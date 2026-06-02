# University Management System

## معرفی پروژه

این پروژه یک سامانه مدیریت دانشگاه است که با استفاده از Java، Hibernate و JPA پیاده‌سازی شده است. در این پروژه موجودیت پایه `Person` تعریف شده و دو موجودیت `Student` و `Teacher` از آن ارث‌بری می‌کنند. همچنین عملیات CRUD از طریق Repository و اعتبارسنجی ورودی‌ها با Hibernate Validator انجام می‌شود.

---

## امکانات پروژه

### Person

کلاس `Person` به عنوان موجودیت پایه دارای ویژگی‌های زیر است:

* First Name
* Last Name
* Birth Date

این موجودیت در پایگاه داده ذخیره می‌شود و پایه‌ای برای سایر کاربران سیستم است.

### Student

کلاس `Student` از `Person` ارث‌بری می‌کند و شامل ویژگی‌های زیر است:

* Student Code
* Major
* Entry Year
* سایر اطلاعات دانشجویی

### Teacher

کلاس `Teacher` از `Person` ارث‌بری می‌کند و شامل ویژگی‌های زیر است:

* Teacher Code
* Education Degree
* Academic Rank (Enum)
* Monthly Salary
* سایر اطلاعات اساتید

---

## ساختار پروژه

### Repository Layer

برای مدیریت داده‌ها یک ریپازیتوری با نام `PersonRepository` پیاده‌سازی شده است که عملیات زیر را ارائه می‌دهد:

```java
save(Person person)
update(Person person)
delete(Person person)
loadAll()
contains(Person person)
```

وظیفه این لایه ارتباط با پایگاه داده و انجام عملیات CRUD می‌باشد.

---

## استراتژی ارث‌بری انتخاب شده

### JOINED Strategy

برای نگاشت ارث‌بری از استراتژی `JOINED` استفاده شده است.

```java
@Inheritance(strategy = InheritanceType.JOINED)
```

### دلیل انتخاب

1. جلوگیری از تکرار داده‌های مشترک در جداول فرزند.
2. نرمال‌سازی بهتر پایگاه داده.
3. نگهداری آسان‌تر ساختار داده‌ها.
4. امکان توسعه آسان سیستم در آینده با افزودن انواع جدید کاربران.
5. مصرف کمتر فضای ذخیره‌سازی نسبت به استراتژی Single Table.

در این روش یک جدول برای `Person` و جداول جداگانه‌ای برای `Student` و `Teacher` ایجاد می‌شود که با کلید خارجی به جدول Person متصل هستند.

---

## Service Layer

برای ثبت‌نام کاربران دو سرویس مجزا در نظر گرفته شده است:

### StudentService

```java
signUp(StudentSignUpDto dto)
```

وظیفه:

* اعتبارسنجی اطلاعات دانشجو
* ایجاد شیء Student
* ذخیره در پایگاه داده

### TeacherService

```java
signUp(TeacherSignUpDto dto)
```

وظیفه:

* اعتبارسنجی اطلاعات استاد
* ایجاد شیء Teacher
* ذخیره در پایگاه داده

---

## Validation

برای اعتبارسنجی داده‌های ورودی از Hibernate Validator استفاده شده است.

نمونه اعتبارسنجی:

```java
@NotBlank
private String firstName;

@NotBlank
private String lastName;

@Past
private LocalDate birthDate;

@NotNull
private String studentCode;
```

### مزایا

* جلوگیری از ورود داده‌های نامعتبر
* کاهش خطاهای سیستمی
* ارائه پیام مناسب به کاربر

نمونه پیام خطا:

```text
نام نمی‌تواند خالی باشد.
تاریخ تولد باید مربوط به گذشته باشد.
کد دانشجویی الزامی است.
حقوق ماهیانه باید بزرگ‌تر از صفر باشد.
```

---

## تکنولوژی‌های استفاده شده

* Java
* Hibernate ORM
* JPA
* Hibernate Validator
* PostgreSQL / MySQL
* Maven

---

## نحوه اجرای پروژه

1. ایجاد پایگاه داده
2. تنظیم اطلاعات اتصال در فایل `hibernate.cfg.xml` یا `application.properties`
3. اجرای Migration ها (در صورت وجود)
4. اجرای برنامه

```bash
mvn clean install
mvn exec:java
```

---

## نتیجه‌گیری

در این پروژه مفاهیم مهم شی‌گرایی و Hibernate شامل:

* Inheritance Mapping
* Repository Pattern
* Service Layer
* DTO Pattern
* Validation
* CRUD Operations

به صورت عملی پیاده‌سازی شده‌اند و ساختار پروژه قابلیت توسعه و نگهداری مناسبی دارد.
