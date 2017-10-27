# SimpleCleanLoginSimple Clean Login
====

## Description

A Simple Clean Login Template with Session Manager for Your Android Project.

## Requierments

 - Android Studio
 - Android SDK API >= 23
 - Java JDK 8
 - Java JRE 8

## Usage
This login is still using local variables. If you want to use username and password from your database, you can do it.

1. Local Login Variables
	- It's located in **LoginActivity.java**
	You can change it whatever you want.
```java
final String IN_USERNAME = "user";
final String IN_PASSWORD = "user";
```

## Changing Background & App Logo
1. Change Login Background
	- Delete old background or overwrite it
	- Drag & drop your background to res > drawable folder and names it bacground.png
2. Change App Logo
	- Drag & drop your logo to res > mipmap folder
	- Put your logo filename in ```android:scr="@mipmap/your_logo```
	```xml
	<ImageView
                android:id="@+id/imgLogo"
                android:layout_width="120dp"
                android:layout_height="130dp"
                android:src="@mipmap/your_logo" />
	```

## Author

- <h4>Simple Clean Login</h4> [zombozo12](https://github.com/zombozo12)

