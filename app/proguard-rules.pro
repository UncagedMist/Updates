# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepattributes InnerClasses
-dontoptimize
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}


-dontwarn android.support.v7.**
-dontwarn android.support.v4.**
-dontwarn com.google.android.gms.**
-dontwarn android.support.v4.media.**

-keepattributes Exceptions, InnerClasses, Serializable, Signature, Deprecated, SourceFile,LineNumberTable, *Annotation*, EnclosingMethod
-dontwarn android.webkit.JavascriptInterface
-dontwarn com.startapp.**

# other
-dontwarn okio.**
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.android.gms.**
-keep class org.wysaid.nativePort.** { *; }
-keep public class * extends java.lang.Exception

-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *; }
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
-keep interface com.squareup.okhttp3.* { *; }
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault
-keep class com.truenet.** {
      *;
}
-dontwarn org.jetbrains.annotations.**

-keep class com.facebook.ads.** { *; }
-dontwarn com.facebook.ads.**

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}
# Glide
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public class * extends com.bumptech.glide.module.AppGlideModule
#-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
#  **[] $VALUES;
#  public *;
#}

# Retrofit
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain service method parameters.
-keepclassmembernames,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}
# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontnote retrofit2.Platform
-dontwarn retrofit2.Platform$Java8

# Joda Time
-dontwarn org.joda.convert.**
-dontwarn org.joda.time.**
-keep class org.joda.time.** { *; }
-keep interface org.joda.time.** { *; }