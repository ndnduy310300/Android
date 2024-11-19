    package com.example.onlineshopping;

    import android.os.Parcel;
    import android.os.Parcelable;

    public class Product implements Parcelable {
        private int id;
        private String name;
        private double price;
        private int imageResourceId;  // Đảm bảo rằng imageResourceId là int, nếu bạn lưu trữ tên hình ảnh thì sẽ cần phải điều chỉnh lại
        private String color;
        private String material;
        private String productCode;

        // Constructor
        public Product(int id, String name, double price, int imageResourceId, String color, String material, String productCode) {
            this.id = id;
            this.name = name;
            this.price = price; // Thêm giá trị price
            this.imageResourceId = imageResourceId;
            this.color = color;
            this.material = material;
            this.productCode = productCode;
        }

        // Getters
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getImageResourceId() {
            return imageResourceId;
        }

        public String getColor() {
            return color;
        }

        public String getMaterial() {
            return material;
        }

        public String getProductCode() {
            return productCode;
        }

        // Parcelable implementation
        protected Product(Parcel in) {
            id = in.readInt();
            name = in.readString();
            price = in.readDouble(); // Đọc giá trị price
            imageResourceId = in.readInt();
            color = in.readString();
            material = in.readString();
            productCode = in.readString();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeString(name);
            dest.writeDouble(price); // Ghi giá trị price
            dest.writeInt(imageResourceId);
            dest.writeString(color);
            dest.writeString(material);
            dest.writeString(productCode);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<Product> CREATOR = new Creator<Product>() {
            @Override
            public Product createFromParcel(Parcel in) {
                return new Product(in);
            }

            @Override
            public Product[] newArray(int size) {
                return new Product[size];
            }
        };
    }
