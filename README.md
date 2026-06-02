# 📍 QuickNear – Hyperlocal Nearby Discovery Android App

🚀 GitHub Repository:  
https://github.com/Sonu-kumar-singh-28/QuickNear-Application  

---

## 🚀 Overview

**QuickNear** is a modern hyperlocal Android application that helps users instantly discover nearby stores, services, and essential categories using real-time GPS location.

Built using **MVVM architecture** and **Jetpack Compose**, the app is designed to be **scalable, maintainable, and production-ready**.

🎯 **Vision:**  
> “Find anything near you — faster, smarter, and more efficiently.”

---

## ✨ Key Features

### 📍 Location Intelligence
- Real-time GPS-based location detection  
- Nearby stores and services discovery  
- Distance-based sorting system  

### 🔍 Smart Search System
- Category-based filtering  
- Intelligent search suggestions  
- Trending and popular results  

### 🏪 Store & Category System
- Dynamic and scalable categories  
- Detailed store listings  
- Banner-based promotions system  

### 🗺️ Map Integration
- Google Maps SDK integration  
- Pin-based location visualization  
- Navigation support to stores  

### ⚡ Modern UI Performance
- Built entirely with Jetpack Compose  
- Lazy-loading optimized lists  
- Reusable composable components  
- Smooth and responsive UI experience  

---

## 🛠️ Tech Stack

### 📱 Android Development
- Kotlin (100%)
- Jetpack Compose
- Material 3 Design System  

### 🧠 Architecture
- MVVM (Model–View–ViewModel)
- Repository Pattern
- Clean Architecture principles  

### 🌐 Data Layer
- Retrofit (API integration)
- Repository-based data flow
- Scalable data handling approach  

### 📍 Location & Maps
- Google Maps SDK
- Fused Location Provider API  

---

## 📂 Project Architecture

```text
com.grocery.delivered.nearbyapp
│
├── domain/
│   ├── model/
│   │   ├── BannerModel
│   │   ├── CategoryModel
│   │   └── StoreModel
│
├── repository/
│   ├── DashboardRepository
│   └── ResultRepository
│
├── screens/
│   ├── dashboard/
│   │   ├── DashboardScreen
│   │   ├── TopBar
│   │   ├── BottomBar
│   │   ├── CategorySection
│   │   └── BannerSection
│   │
│   ├── map/
│   │   └── MapScreen
│   │
│   └── results/
│       ├── ResultList
│       ├── NearestList
│       ├── PopularList
│       └── SearchScreen
│
├── viewmodel/
├── ui.theme/
└── MainActivity.kt
