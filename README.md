# mapsted-cordova-plugin

Cordova/Ionic plugin for the Mapsted SDK. This release targets the **native Mapsted SDK 26.7.1**.

## Installation

```bash
cordova plugin add https://github.com/MapstedHQ/mapsted-cordova-plugin.git
```

## Consumer requirements (breaking — documented)

The Mapsted SDK 26.7.1 native artifacts set a hard toolchain floor:

| | Minimum |
|---|---|
| Android `minSdkVersion` | **26** |
| Android Gradle Plugin | **8.9.1** |
| Kotlin | **2.3.20** |
| Gradle | **8.13** |
| iOS deployment target | **16.0** |

## Platform Specific Configurations

### iOS

- Add the pod sources to the top of `platforms/ios/Podfile`:

```sh
source 'https://cdn.cocoapods.org/'
source 'https://github.com/MapstedHQ/podspec.git'

platform :ios, '16.0'
```

- Set use frameworks in your app target:

```sh
use_frameworks!
```

#### IMPORTANT
- Add license file in Resources folder `your_ios_license.key`


### Android

#### IMPORTANT
- Add license file in Assets folder (`/app/src/main/assets`) `your_android_license.key`



## API

<docgen-index>

* [`launchMapActivity()`](#addlistenerinitcallback)

</docgen-index>
