# Kotlin Section Adapter

This library is written in Kotlin.
Special RecyclerView.Adapter that can render many different kind of items.
Thanks to to this library you have an Adapter which will handle rendering of multiple different items.

## Problem Solved

How often does it happen in your project when you have to render several different items in the same adapter?
Many times your list have to have a header or sections, or simply you want to create a screen with sections. 
You have to extend `RecyclerView.Adapter` this usually makes the code complicated and unreadable. If you don't structure your implementation you will have a very ugly legacy code in future.


## Sample Project

#### Google Play

<a href="https://play.google.com/store/apps/details?id=com.morcinek.kotlin.adapter">
  <img width="200px" alt="Follow me on Google+"
       src="http://www.morcinek.co.uk/wp-content/uploads/2014/11/Android-app-on-google-play.svg" />
</a>


![Alt text](raw/screenshot-budget-adapter.png?raw=true "Section Adapter")

## How it works

#### Create `ViewModel` Class
```kotlin
class HeaderViewModel(val title: String, val value: String)
```


#### Create specific `SectionViewAdapter`
```kotlin
class HeaderSectionViewAdapter : SectionRecyclerViewAdapter.SectionViewAdapter<HeaderViewModel, HeaderSectionViewAdapter.ViewHolder> {

    override fun onBindViewHolder(holder: ViewHolder, item: HeaderViewModel, position: Int) {
        holder.title.text = item.title
        holder.value.text = item.value
    }

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int)
            = ViewHolder(layoutInflater.inflate(R.layout.budget_header, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
            get() = itemView.title
        val value: TextView
            get() = itemView.value
    }
}
```

#### Add `SectionViewAdapter` to `SectionRecyclerViewAdapter`
```kotlin
    val adapter = SectionRecyclerViewAdapter().apply {
        addSectionViewAdapter(HeaderSectionViewAdapter())
    }
```

#### Set data to `SectionRecyclerViewAdapter`
```kotlin
    adapter.setList(listOf(
            HeaderViewModel("Monthly Budget", "$5217"),
            HeaderViewModel("Weekly Budget", "$678")
    ))
```

## How to Import 
### Gradle
```groovy
dependencies {
    compile 'com.github.tmorcinek:kotlin-section-adapter:1.x'
}
```


## Reference

[Getting started with Android and Kotlin] (https://kotlinlang.org/docs/tutorials/kotlin-android.html)

[Try Kotlin] (https://try.kotlinlang.org/)

[Kotlin.org] (https://kotlinlang.org/)


## Developed By

Tomasz Morcinek https://github.com/tmorcinek

<a href="https://plus.google.com/+TomaszMorcinek">
  <img alt="Follow me on Google+"
       src="https://github.com/tmorcinek/kotlin-section-adapter/tree/master/raw/google-plus-logo.png" />
</a>
<a href="https://play.google.com/store/apps/developer?id=Tomasz+Morcinek">
  <img alt="Checkout my Applications in Google Play"
       src="https://github.com/tmorcinek/kotlin-section-adapter/tree/master/raw/google-play-logo.png" />
</a>


## License

    Copyright 2017 Tomasz Morcinek.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http:/``/www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
