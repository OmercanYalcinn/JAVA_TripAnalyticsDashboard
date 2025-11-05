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


## Yazar

Bu proje kişisel bir öğrenme ve veri hazırlama çalışmasıdır.
Veriler yalnızca örnek amaçlıdır.

## Gelecekteki Geliştirmeler (Opsiyonel)

GUI veya web tabanlı dashboard entegrasyonu

Seyahat verisi analizi (ortalama hız, rota çizimi vb.)

Veritabanı istatistikleri için REST API
