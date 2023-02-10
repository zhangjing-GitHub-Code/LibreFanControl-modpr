﻿using System.Collections;
using HardwareDaemon.Hardware;
using HardwareDaemon.Model;
using HardwareDaemon.Proto;

namespace HardwareDaemon;

internal enum ServiceState
{
    Open,
    Close
}
internal static class Program
{
    private static readonly ArrayList HControls = new();
    private static readonly ArrayList HTemps = new();
    private static readonly ArrayList HFans = new();

    private static readonly ArrayList Controls = new();
    private static readonly ArrayList Behaviors = new();
    private static readonly ArrayList CustomTemps = new();

    private static readonly ArrayList UpdateList = new();

    private static readonly Settings Settings = SettingsHelper.LoadSettingsFile();

    private static bool IsOpen = false;

    private static void Main()
    {
        HardwareManager.Start(HControls, HTemps, HFans);

        var confId = Settings.ConfId;
        
        if (confId == null)
        {
            Console.WriteLine("conf Id == null -> return");
            return;
        }


        ConfHelper.LoadConfFile(confId, Controls, Behaviors, CustomTemps);

        Update.CreateUpdateList(
            UpdateList,
            HControls,
            HTemps,
            Controls,
            Behaviors,
            CustomTemps
        );
        
        HardwareManager.Update();
        Update.UpdateUpdateList(UpdateList);
        UpdateJob();
    }


    public static bool ServiceShouldStop = false;
    private static void UpdateJob()
    {
        while (!ServiceShouldStop)
        {
            HardwareManager.Update();
            if (IsOpen)
            {
                Update.UpdateAllSensors(HControls, HTemps, HFans);
            }
            Update.UpdateUpdateList(UpdateList);
            
            Thread.Sleep(Settings.UpdateDelay * 1000);
        }
    }
}