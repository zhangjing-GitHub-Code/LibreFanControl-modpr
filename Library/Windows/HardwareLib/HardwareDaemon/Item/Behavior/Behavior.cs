﻿using HardwareDaemon.Hardware.Sensor;

namespace HardwareDaemon.Item.Behavior;

public enum BehaviorType
{
    Flat,
    Linear,
    Target
}

public class BehaviorException : Exception
{
    public BehaviorException(string msg) : base(msg)
    {
    }
}

public abstract class Behavior
{
    protected Behavior(string id, BehaviorType type)
    {
        Id = id;
        Type = type;
    }

    public string Id { get; }
    public BehaviorType Type { get; }
}

public abstract class BehaviorWithTemp : Behavior
{
    private const string CustomTempPrefix = "iCustomTemp";

    protected BehaviorWithTemp(string id, string? tempId, BehaviorType type) : base(id, type)
    {
        TempId = tempId;
        IsValid = TempId != null;

        if (!IsValid) return;

        var parts = TempId!.Split('/');
        IsCustomTemp = parts.Length > 0 && parts[0] == CustomTempPrefix;
    }

    private string? TempId { get; }
    private bool IsCustomTemp { get; }

    private bool IsValid { get; }

    private BaseSensor? Htemp { get; set; }
    private CustomTemp? CustomTemp { get; set; }


    protected int GetSensorValue()
    {
        if (IsCustomTemp) return CustomTemp!.GetValue();

        Htemp!.Update();
        return Htemp.Value;
    }


    public abstract int GetSpeed();


    public void Init()
    {
        if (!IsValid)
            throw new BehaviorException("behavior not valid");

        if (IsCustomTemp)
            foreach (var iCustomTemp in State.CustomTemps.Values)
            {
                if (iCustomTemp.Id != TempId) continue;

                iCustomTemp.Init();
                CustomTemp = iCustomTemp;
                return;
            }
        else
            foreach (var hTemp in State.HTemps.Values)
            {
                if (hTemp.Id != TempId) continue;

                Htemp = hTemp;
                return;
            }

        throw new BehaviorException("no sensor found (should not happened)");
    }
}