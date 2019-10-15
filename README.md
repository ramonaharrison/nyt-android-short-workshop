# Android Development Workshop

## The Goal

Build a NYTimes app using the [public API](https://developer.nytimes.com/):

https://developer.nytimes.com/

<img src="images/app.png" width="250">


## Implementation

In `activity_main.xml`, give an `id` to the `TextView`

```xml
<TextView
    android:id="@+id/myTextView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:text="Hello World!"
    app:layout_constraintBottom_toBottomOf="parent"
    app:layout_constraintLeft_toLeftOf="parent"
    app:layout_constraintRight_toRightOf="parent"
    app:layout_constraintTop_toTopOf="parent" />
```

In `MainActivity.kt`, set the text of `myTextView` to the API response.

```kotlin
// Map the section content from the response body.
response.body()?.let { section ->
    // Print the contents of the section in the debug logs.
    Log.d("MainActivity", section.toString())

    // Display the contents of the section in the TextView
    myTextView.text = section.toString()
}
```

---
