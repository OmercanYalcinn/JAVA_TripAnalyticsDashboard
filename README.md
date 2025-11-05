# 📊 TripAnalyticsDashboard

**TripAnalyticsDashboard**, araç seyahat verilerini (örneğin GPS konumları, hız, yön gibi bilgiler) CSV dosyasından okuyup **SQLite veritabanına** yükleyen basit bir **Java** projesidir.  
Amaç, ham seyahat verilerini düzenli bir veritabanı yapısına dönüştürerek analiz, raporlama veya dashboard geliştirme aşamalarına temel hazırlamaktır.

---

## Özellikler

- **SQLite veritabanı** oluşturur (`data/trips.db`).
- Gerekli tabloları otomatik olarak hazırlar:
  - `raw_trajectories`: ham GPS verilerini tutar  
  - `trips`: seyahatlerin özet bilgilerini saklar
- CSV dosyasındaki verileri **toplu (batch)** olarak yükler.
- Eğer tabloda veri varsa CSV import işlemini atlar (tekrar yükleme yapmaz).
- `try-with-resources` yapısı ile güvenli bağlantı ve kaynak yönetimi sağlar.

---

## Kod Bileşenleri

| Sınıf | Görevi |
|-------|--------|
| `DatabaseInitializer` | Veritabanı bağlantısı kurar ve tabloları oluşturur. |
| `CSVLoader` | CSV dosyasını okuyup `raw_trajectories` tablosuna veri yükler. |
| `Main` | Tüm süreci başlatır: veritabanını hazırlar, CSV'den veri aktarır. |

---

## 📁 Proje Yapısı

TripAnalyticsDashboard/
 ├── src/
 │   ├── db/
 │   │   ├── DatabaseInitializer.java
 │   │   └── CSVLoader.java
 │   └── Main.java
 ├── data/              (otomatik oluşturulur)
 ├── dataset/           (CSV dosyası burada bulunur)
 ├── README.md
 └── .gitignore

 ## 🗂️ Veri Seti

Bu projede kullanılan örnek veri, **[Kaggle - CityTrek-14K: A Large Dataset of 14k GPS Trajectories Covering 4.8k Hours of Driving](https://www.kaggle.com/datasets/sobhanmoosavi/citytrek-14k)** isimli açık veri setinden alınmıştır.  
Veri seti, şehir genelinde sürüş rotaları, hız, konum ve zaman bilgilerini içeren **GPS tabanlı seyahat kayıtlarını** içerir.  

CSV dosyası (`trajectories_to_publish.csv`), bu projede **örnek giriş verisi** olarak kullanılmıştır.  
Amaç, ham konum verilerini veritabanına aktarıp daha ileri analiz veya dashboard geliştirme süreçleri için hazır hale getirmektir.


## Yazar

Bu proje kişisel bir öğrenme ve veri hazırlama çalışmasıdır.
Veriler yalnızca örnek amaçlıdır.

## Gelecekteki Geliştirmeler (Opsiyonel)

GUI veya web tabanlı dashboard entegrasyonu

Seyahat verisi analizi (ortalama hız, rota çizimi vb.)

Veritabanı istatistikleri için REST API
