# LibreFanControl


- Open source
- Service very low on ressource (~30mb)
- Display sensors data on real time
- Control fans based on custom behaviors
- Save configuration
- Multiplatform (Linux/Windows)


![mainPageV1](https://github.com/wiiznokes/LibreFanControl/assets/78230769/543af76c-137c-456d-a04e-8ebfed323178)



## Requirements
You will need some component of [dotnet 7](https://dotnet.microsoft.com/en-us/download/dotnet/7.0)

- `ASP.NET Core Runtime`
- `.NET Runtime`

You will need to run the app with admin privilege on both Linux and Windows.

If you have any troubles, check out the [faq](./assets/faq.md).

**Issues, enhancement requests and PR are welcomed!**


## Configurations files

#### Windows
- `C:\ProgramData\LibreFanControl`
#### Linux
- `/etc/LibreFanControl`

On Linux, maybe you will need to download libsensors and execute sensors-detect.


## Build
- On Windows, if you want to build the project, you will just need the `SDK` of [dotnet 7](https://dotnet.microsoft.com/en-us/download/dotnet/7.0). 
- On Linux, you will need a [JDK](https://jdk.java.net/java-se-ri/17).

The needed commands can be found in CI directory.


## Plans (not in this order)

- [x] Support Linux
- [ ] Use type safe strings and icons ([moko-resources](https://github.com/icerockdev/moko-resources) ?)
- [ ] Add a system to automatically receive updates
- [ ] Add workflow to the project (CI/CD, ect...) ([docker](https://docs.docker.com/) ?)
- [ ] Implement settings (info, help)
- [ ] Add graph behavior (abscissa -> temp, ordinate -> fan speed)
- [ ] Write an intelligent program to bind controls to their fans, and make a nice first config
- [ ] Support [Flatpak](https://docs.flatpak.org/en/latest/first-build.html)

## Library used

### UI
- [Compose Multiplatform Desktop](https://www.jetbrains.com/lp/compose-mpp/)
- [setting-sliding-windows](https://github.com/wiiznokes/setting-sliding-windows)
### SENSORS
##### Windows
- [LibreHardwareMonitor](https://github.com/LibreHardwareMonitor/LibreHardwareMonitor)
##### Linux
- [lm-sensors](https://github.com/lm-sensors/lm-sensors)
