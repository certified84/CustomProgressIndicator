# CustomProgressIndicator
![Jit](https://img.shields.io/jitpack/v/github/certified84/CustomProgressIndicator?style=for-the-badge&color=2F9319) 

 A custom progress indicator view to give your android application a nice feel.


## Setup

Add it in your root `build.gradle` at the end of repositories:

```groovy
allprojects {
    repositories {
        //...omitted for brevity
        maven { url 'https://jitpack.io' }
    }
}
```



Add the dependency

```groovy
dependencies {
   implementation "com.github.certified84:CustomProgressIndicator:$latest_release"
}
```

## :bulb: Tech Used

<img src="https://marvel-b1-cdn.bc0a.com/f00000000156946/www.jrebel.com/sites/rebel/files/image/2021-01/what%20is%20kotlin%20banner%20image.png" height="70px" width="100px"> 

## Demo
<img src="https://user-images.githubusercontent.com/20203694/137814254-97e4aec8-02cb-4c00-8624-ddc24a80f966.gif" alt="demo"  width="300" height="300"/><img src="https://user-images.githubusercontent.com/20203694/137814196-01499fa0-9f27-4797-bafe-6530d2e3f743.gif" alt="demo"  width="300" height="300"/>
    
## Usage
Sample implementation [here](app/)

### CustomProgressIndicator
- Add `CustomProgressIndicator` to your xml layout.

```xml
    <com.certified.customprogressindicatorlibrary.CustomProgressIndicator
         android:id="@+id/indicator"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:elevation="4dp" />
```
- Make sure it fills the width and height of the parent view

### Initialize the indicator

```kotlin
    var indicator: CustomProgressIndicator = findViewById(R.id.indicator)
    
    override fun onResume() {
        super.onResume()
        indicator.startAnimation()
   }
```

### Set the indicator text size

```kotlin
   indicator.setTextSize(resources.getDimension(R.dimen.30sp))
```

### Set the indicator text color

```kotlin
   indicator.setTextColor(ResourcesCompat.getColorStateList(resources, R.color.white, null)!!)
```

### Set the indicator text

```kotlin
   indicator.setText("Loading...")
```

### Set the indicator text typeface

```kotlin
   indicator.setTypeface(R.font.space_grotesk_regular)
```

### Set the indicator progress color

```kotlin
   indicator.setProgressIndicatorColor("#FFFFFF")
```

### Set the indicator track color

```kotlin
   indicator.setTrackColor("#B32821")
```

### Set the indicator image resource

```kotlin
   indicator.setImageResource(R.drawable.ic_logo)
```


### Licensed under the [Apache-2.0 License](LICENSE)
