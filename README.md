# ⭐ Star_Android_Library

**Star_Android_Library** is an Android custom view library that allows developers to create and customize star ratings with ease. You can specify colors, radius, and other appearance properties to suit your app’s theme, all from XML or programmatically within your Kotlin code. This library is built with flexibility in mind, making it easy to integrate and style star ratings in your Android application.

## ✨ Features
- Customizable star ratings
- Adjustable colors, inner and outer radii, and sizes
- Supports both XML-based and programmatic customization
- Easy integration via JitPack

## 🚀 Getting Started

To start using **Star_Android_Library** in your project, follow these steps.

### Step 1: Add the JitPack Repository

To integrate this library, first add the JitPack repository to your project. Open your `settings.gradle` or `build.gradle` file at the project level and add the following lines in the `repositories` block:

```gradle
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### Step 2: Add the Dependency
In your module's `build.gradle` file, add the dependency for the library:
```
dependencies {
    implementation 'com.github.dhairyapandya05:Star_Android_Library:1.2'
}

```
Replace Tag with the latest release tag or commit hash if no GitHub release is available.
## 🎨 Usage

### Step 1: Add `RatingView` to Your Layout XML
To display a rating view with stars, simply add the RatingView to your layout XML file. You can customize it by setting colors, outer radius, and inner radius directly in XML.

```
<com.example.ratinglibrary.RatingView
    android:id="@+id/ratingView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:selectedColor="@color/selected_star_color"
    app:unselectedColor="@color/unselected_star_color"
    app:outerRadius="80dp"
    app:innerRadius="40dp" />

```

### Step 2: Customize `RatingView` Programmatically (Optional)
You can also control the appearance of `RatingView` from your Kotlin or Java code in `MainActivity`:

```
val ratingView = findViewById<RatingView>(R.id.ratingView)

// Set properties dynamically
ratingView.selectedColor = Color.RED
ratingView.unselectedColor = Color.GRAY
ratingView.outerRadius = 100f
ratingView.innerRadius = 50f

// Refresh the view if necessary
ratingView.invalidate()

```

### Step 3: Configure Interactivity (Optional)
Implement custom touch listeners or handle touch interactions based on your app's requirements. You can define actions when a star is selected or changed.

## 📝 Attributes

This library provides the following customizable attributes for `RatingView`:

| Attribute         | Description                                  | Type       | Default Value    |
|-------------------|----------------------------------------------|------------|------------------|
| `selectedColor`   | Color for selected stars                     | color      | Yellow           |
| `unselectedColor` | Color for unselected stars                   | color      | Light Gray       |
| `outerRadius`     | Radius of the outer circle (star size)       | dimension  | 80dp             |
| `innerRadius`     | Radius of the inner circle (empty area)      | dimension  | 40dp             |

## Sample 

![2024-11-08-22-23-24_1](https://github.com/user-attachments/assets/2930d602-b084-4ef6-a90e-d6905c83e1e8)

## 📦 Dependency Management

### JitPack Build Process

When you add the JitPack dependency, JitPack will automatically check out the code, build it, and serve the artifacts (JAR, AAR) the first time it is requested. This means that if no GitHub Releases are available, you can use the short commit hash or `master-SNAPSHOT` as the version.

## 🌟 Example Project

To see this library in action, clone this repository and run the example project included. The example demonstrates various ways to customize and use `RatingView` to suit different application needs.

## 📄 License

This library is open source and licensed under the MIT License. See the `LICENSE` file for more details.

## 🛠️ Contributing

We welcome contributions! If you'd like to improve this library, please fork the repository and create a pull request.

---

Happy coding! 😃 Enjoy using **Star_Android_Library** for all your star-rating needs!
