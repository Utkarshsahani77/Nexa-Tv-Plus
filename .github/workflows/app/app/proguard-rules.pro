# Media3
-keep class androidx.media3.** { *; }

# Retrofit
-keepattributes Signature
-keepattributes *Annotation*

-dontwarn okhttp3.**
-dontwarn okio.**

# Gson
-keep class com.google.gson.** { *; }
-keep class * implements java.lang.reflect.Type

# Room
-keep class * extends androidx.room.RoomDatabase
-dontwarn androidx.room.paging.**
