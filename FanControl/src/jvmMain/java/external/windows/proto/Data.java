// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: data.proto

package external.windows.proto;

public final class Data {
  private Data() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Device_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Device_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_DeviceList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_DeviceList_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_SetControl_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_SetControl_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Update_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Update_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UpdateList_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UpdateList_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\ndata.proto\"@\n\006Device\022\014\n\004Name\030\001 \001(\t\022\r\n\005" +
      "Index\030\002 \001(\005\022\n\n\002Id\030\003 \001(\t\022\r\n\005Value\030\004 \001(\005\"y" +
      "\n\nDeviceList\022$\n\004Type\030\001 \001(\0162\026.DeviceList." +
      "DeviceType\022\027\n\006Device\030\002 \003(\0132\007.Device\",\n\nD" +
      "eviceType\022\013\n\007CONTROL\020\000\022\007\n\003FAN\020\001\022\010\n\004TEMP\020" +
      "\002\"=\n\nSetControl\022\020\n\010LibIndex\030\001 \001(\005\022\016\n\006isA" +
      "uto\030\002 \001(\010\022\r\n\005Value\030\003 \001(\005\"&\n\006Update\022\r\n\005In" +
      "dex\030\001 \001(\005\022\r\n\005Value\030\002 \001(\005\"%\n\nUpdateList\022\027" +
      "\n\006Update\030\001 \003(\0132\007.UpdateB(\n\026external.wind" +
      "ows.protoB\004DataP\001\252\002\005Protob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Device_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Device_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Device_descriptor,
        new java.lang.String[] { "Name", "Index", "Id", "Value", });
    internal_static_DeviceList_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_DeviceList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_DeviceList_descriptor,
        new java.lang.String[] { "Type", "Device", });
    internal_static_SetControl_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_SetControl_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_SetControl_descriptor,
        new java.lang.String[] { "LibIndex", "IsAuto", "Value", });
    internal_static_Update_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_Update_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Update_descriptor,
        new java.lang.String[] { "Index", "Value", });
    internal_static_UpdateList_descriptor =
      getDescriptor().getMessageTypes().get(4);
    internal_static_UpdateList_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UpdateList_descriptor,
        new java.lang.String[] { "Update", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
