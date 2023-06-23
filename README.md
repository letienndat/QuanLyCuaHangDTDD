## Hướng dẫn cài đặt chương trình:
#### Yêu cầu: 
- Cài đặt Java JDK 18 trở lên
- MySQL
- Intellij IDEA

### Các bước cài đặt:
- Clone hoặc tải xuống repo
- Mở project bằng Intellij IDEA và thêm các thư viện (file JAR) trong thư mục lib vào project
- Có thể chạy file create_data.sql trong thư mục sql để khởi tạo Databse, các bảng cần thiết và khởi tạo giá trị mặc định
- Mở file src/com/baitaplon/script/InfoConnectDatabase: Thay đổi DATABASE_NAME, USERNAME và PASSWORD để kết nối tới MySQL phù hợp

### Chạy chương trình:
- Mở file src/com/baitaplon/script/main/Main.java: Chạy file này
- Màn hình đăng nhập được hiển thị
- Tài khoản Quản trị viên mặc định (Không cần khởi tạo):
``` bash
    Tài khoản: admin
    Mật khẩu: admin
```
- Tài khoản cá nhân có thể tạo ở tab Đăng ký
### Hình ảnh chương trình
<img src="/image_readme/1.PNG">
<img src="/image_readme/2.PNG">