# CustomProgressIndicator
![Jit](https://img.shields.io/jitpack/v/github/certified84/CustomProgressIndicator?style=for-the-badge&color=2F9319) 

 A custom progress indicator view to give your android application a nice feel.

## Demo
<img src="https://github.com/certified84/CustomProgressIndicator/blob/master/demo/custom_progress_indicator.gif" alt="demo"/>


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
- The elevation tag ensures it stays above views like buttons and cards

### Initialize the indicator

```kotlin
    var indicator: CustomProgressIndicator = findViewById(R.id.indicator)
    // ViewBinding
    var indicator = binding.indicator
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

### Start the animation when the view is visible

```kotlin

   // Perform operation that needs loading, show the view and start the animation
   if(isLoading) {
     indicator.apply {
       visibility = View.Visible
       startAnimation()
     }
   }
   
   // Always check if the view is visible in onResume and start the animation
   override fun onResume() {
       super.onResume()
       if(indicator.isVisible)
         indicator.startAnimation()
       //...omitted for brevity
    }
```

### Stop the animation when the view isn't visible

```kotlin
   indicator.stopAnimation()
   
   // Always stop the animation in onPause()
   override fun onPause() {
       super.onPause()
       indicator.stopAnimation()
       //...omitted for brevity
    }
```


## Contibuting

- Please create an issue if you find something wrong
- Feel free to contibute. Read the guide [here](CONTIBUTION)


### Licensed under the [Apache-2.0 License](LICENSE)

```

Copyright 2022 Samson Achiaga

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```
