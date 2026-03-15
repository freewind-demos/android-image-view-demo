# Android ImageView 图片视图演示

## 简介

本 Demo 演示 ImageView 的基本用法和多种缩放类型（ScaleType）。

## 基本原理

ImageView 是 Android 中用于显示图片的组件，是 ImageButton、ZoomButton 等组件的基类。它可以显示来自资源文件、文件路径或网络 URL 的图片。

ImageView 的核心属性：
- `src`：设置图片资源
- `scaleType`：设置图片缩放类型
- `tint`：设置图片着色（颜色滤镜）
- `adjustViewBounds`：调整视图边界以匹配图片尺寸

## 启动和使用

### 环境要求
- Android Studio
- JDK 17
- Gradle 8.x

### 安装和运行

1. 用 Android Studio 打开项目
2. 连接 Android 设备或模拟器
3. 点击 Run 运行

### 使用方法
- 运行后将看到一个居中裁剪的图片

## 教程

### 什么是 ImageView？

ImageView 是 Android 中最常用的图片显示组件，几乎每个应用都会用到它。无论是应用图标、头像、图片列表，还是按钮图标，都离不开 ImageView。

ImageView 的主要功能：
- 显示各种来源的图片（本地资源、文件、网络）
- 控制图片的缩放和显示方式
- 与触摸事件结合实现图片查看器
- 显示图片加载状态和占位图

### ScaleType 缩放类型详解

ImageView 提供了 8 种缩放类型，每种都有不同的显示效果：

1. **matrix** - 使用矩阵变换
   - 不进行任何缩放，需要手动设置矩阵
   - 可以实现任意变换效果

2. **fitXY** - 拉伸填充
   - 将图片拉伸填满整个 ImageView
   - 可能会改变图片的宽高比
   - 适用于需要完全填满容器的场景

3. **fitStart** - 适应起始位置
   - 等比缩放，图片完整显示
   - 缩放后靠左上角对齐
   - 保持图片宽高比

4. **fitCenter** - 适应居中（常用）
   - 等比缩放，图片完整显示
   - 居中显示
   - 保持图片宽高比，是默认选项

5. **fitEnd** - 适应结束位置
   - 等比缩放，图片完整显示
   - 缩放后靠右下角对齐
   - 保持图片宽高比

6. **center** - 居中不缩放
   - 不进行缩放，居中显示
   - 图片大于容器时会被裁剪
   - 适用于大图查看

7. **centerCrop** - 居中裁剪（常用）
   - 等比缩放，直到填满容器
   - 超出部分被裁剪
   - 保持图片宽高比，适用于封面图

8. **centerInside** - 居中缩小
   - 如果图片大于容器，等比缩小到容器内
   - 如果图片小于容器，保持原大小居中
   - 保证图片完整显示

### 在 XML 中设置图片

```xml
<ImageView
    android:id="@+id/imageView"
    android:layout_width="200dp"
    android:layout_height="200dp"
    android:src="@drawable/my_image"
    android:scaleType="centerCrop" />
```

### 在代码中设置图片

```kotlin
// 从资源文件加载
imageView.setImageResource(R.drawable.my_image)

// 从文件加载
imageView.setImageURI(Uri.fromFile(file))

// 从 Drawable 加载
imageView.setImageDrawable(drawable)

// 使用 Bitmap
imageView.setImageBitmap(bitmap)
```

### 设置图片着色

可以为图片设置颜色滤镜，实现一些特殊效果：

```kotlin
// 设置 tint 颜色
imageView.setColorFilter(Color.RED)

// 清除 tint
imageView.clearColorFilter()

// 使用 PorterDuff 模式
imageView.setColorFilter(Color.RED, PorterDuff.Mode.MULTIPLY)
```

### 注意事项

1. **图片大小**：加载大图前建议压缩，避免 OOM（内存溢出）
2. **占位图**：网络加载时建议设置占位图和错误图
3. **适配**：考虑不同屏幕密度的图片资源
4. **缓存**：频繁使用的图片建议使用内存缓存

## 关键代码详解

### activity_main.xml

```xml
<!-- 根布局：垂直线性布局，居中显示 -->
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:padding="16dp"
    android:gravity="center">

    <!-- 标题 -->
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="ImageView 图片视图演示"
        android:textSize="20sp"
        android:textStyle="bold"
        android:paddingBottom="16dp" />

    <!-- ImageView 组件 -->
    <ImageView
        android:id="@+id/imageView"
        android:layout_width="200dp"
        android:layout_height="200dp"
        <!-- scaleType: 居中裁剪，保持比例填满容器 -->
        android:scaleType="centerCrop" />
</LinearLayout>
```

### MainActivity.kt

```kotlin
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. 获取 ImageView 组件
        val imageView = findViewById<ImageView>(R.id.imageView)

        // 2. 设置图片资源
        // 使用系统内置的图片资源（相册图标）
        imageView.setImageResource(android.R.drawable.ic_menu_gallery)
    }
}
```

### ScaleType 效果对比图

```
原图尺寸: 400x300
容器尺寸: 200x200

fitXY:       拉伸到 200x200（变形）
fitCenter:   缩放到 200x133，居中显示
centerCrop:  缩放到 267x200，裁剪多余部分
center:      居中显示 400x300，超出部分裁剪
```
