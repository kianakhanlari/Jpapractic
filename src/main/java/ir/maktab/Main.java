package ir.maktab;

import ir.maktab.model.Teacher;
import ir.maktab.model.dto.StudentDto;
import ir.maktab.model.dto.TeacherDto;
import ir.maktab.repository.impl.PersonRepositoryImpl;
import ir.maktab.repository.impl.TeacherRepositoryImpl;
import ir.maktab.service.PersonService;
import ir.maktab.service.TeacherService;
import ir.maktab.service.TransactionManagerImpl;

import ir.maktab.util.ApplicationContext;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf =
                ApplicationContext.getEntityManagerFactory();
        TransactionManagerImpl t=new TransactionManagerImpl(emf);
        PersonRepositoryImpl repo=new PersonRepositoryImpl(t);
        PersonService personServic=new PersonService(repo,t);
        TeacherRepositoryImpl rep=new TeacherRepositoryImpl(t);
        TeacherService  teacherService=new TeacherService(rep,t);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== منوی اصلی =====");
            System.out.println("1. دانش‌آموز");
            System.out.println("2. معلم");
            System.out.println("0. خروج");
            System.out.print("انتخاب کنید: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            if (mainChoice == 0) {
                System.out.println("خروج از برنامه...");
                break;
            }

            switch (mainChoice) {
                case 1 -> studentMenu(scanner, personServic);
                case 2 -> teacherMenu(scanner, teacherService);
                default -> System.out.println("انتخاب نامعتبر است!");
            }
        }

        scanner.close();
    }


    private static void studentMenu(Scanner scanner, PersonService personService) {
        while (true) {
            System.out.println("\n--- منوی دانش‌آموز ---");
            System.out.println("1. ثبت دانش‌آموز جدید");
            System.out.println("2. حذف دانش‌آموز");
            System.out.println("3. ویرایش دانش‌آموز");
            System.out.println("4. مشاهده همه دانش‌آموزان");
            System.out.println("5. مشاهده همه افراد");
            System.out.println("0. بازگشت به منوی اصلی");
            System.out.print("انتخاب کنید: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {

                    StudentDto dto = new StudentDto();
                    System.out.print("نام: ");
                    dto.firstName = scanner.nextLine();
                    System.out.print("نام خانوادگی: ");
                    dto.lastName = scanner.nextLine();
                    System.out.print("فیلد تحصیلی: ");
                    dto.field = scanner.nextLine();
                    System.out.print("شماره دانش‌آموزی: ");
                    dto.student_Code = scanner.nextLong();
                    scanner.nextLine();
                    personService.save(dto);
                    System.out.println("دانش‌آموز با موفقیت ثبت شد!");
                }
                case 2 -> {

                    System.out.print("شماره دانش‌آموزی جهت حذف: ");
                    Long studentCode = scanner.nextLong();
                    scanner.nextLine();
                    personService.delete(studentCode);
                    System.out.println("دانش‌آموز حذف شد!");
                }
                case 3 -> {

                    StudentDto dto = new StudentDto();
                    System.out.print("شماره دانش‌آموزی جهت ویرایش: ");
                    dto.student_Code = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("نام جدید: ");
                    dto.firstName = scanner.nextLine();
                    System.out.print("نام خانوادگی جدید: ");
                    dto.lastName = scanner.nextLine();
                    personService.update(dto);
                    System.out.println("ویرایش دانش‌آموز انجام شد!");
                }
                case 4 -> personService.showAllStudents();
                case 5 -> personService.showAllPerson();
                case 0 -> {
                    System.out.println("بازگشت به منوی اصلی...");
                    return;
                }
                default -> System.out.println("انتخاب نامعتبر است!");
            }
        }
    }


    private static void teacherMenu(Scanner scanner,  TeacherService  teacherService) {
        while (true) {
            System.out.println("\n--- منوی معلم ---");
            System.out.println("1. ثبت معلم جدید");
            System.out.println("2. حذف معلم");
            System.out.println("3. ویرایش معلم");
            System.out.println("4. مشاهده همه معلمان");
            System.out.println("5. مشاهده همه افراد");
            System.out.println("0. بازگشت به منوی اصلی");
            System.out.print("انتخاب کنید: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {

                    TeacherDto dto = new TeacherDto();
                    System.out.print("نام: ");
                    dto.firstName = scanner.nextLine();
                    System.out.print("نام خانوادگی: ");
                    dto.lastName = scanner.nextLine();
                    System.out.print("حقوق ");
                    dto.salary = scanner.nextDouble();
                    System.out.print("کد معلم: ");
                    dto.teacher_Code = scanner.nextLong();
                    scanner.nextLine();
                    teacherService.save(dto);
                    System.out.println("معلم با موفقیت ثبت شد!");
                }
                case 2 -> {
                    System.out.print("کد معلم جهت حذف: ");
                    Long code = scanner.nextLong();
                    scanner.nextLine();
                    teacherService.delete(code);
                    System.out.println("معلم حذف شد!");
                }
                case 3 -> {
                    TeacherDto dto = new TeacherDto();
                    System.out.print("کد معلم جهت ویرایش: ");
                    dto.teacher_Code = scanner.nextLong();
                    scanner.nextLine();
                    System.out.print("نام جدید: ");
                    dto.firstName = scanner.nextLine();
                    System.out.print("نام خانوادگی جدید: ");
                    dto.lastName = scanner.nextLine();
                    teacherService.update(dto);
                    System.out.println("ویرایش معلم انجام شد!");
                }
                case 4 -> {
                    List<Teacher> list = teacherService.showAllTeacher();

                    if (list.isEmpty()) {
                        System.out.println("هیچ معلمی ثبت نشده است.");
                    } else {
                        System.out.println("لیست معلمان:");
                        for (Teacher teacher : list) {
                            System.out.println(teacher);
                        }
                    }
                }


                case 0 -> {
                    System.out.println("بازگشت به منوی اصلی...");
                    return;
                }
                default -> System.out.println("انتخاب نامعتبر است!");
            }
        }
    }
}