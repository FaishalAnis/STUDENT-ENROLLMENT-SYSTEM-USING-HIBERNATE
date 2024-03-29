package com.anudip.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
//import javax.persistence.Query;
import java.util.logging.*;
import com.anudip.app.dao.AppliedStudentDAO;
import com.anudip.app.dao.CourseDAO;
import com.anudip.app.dao.EnrolledStudentDAO;
import com.anudip.app.dao.LecturerDAO;
import com.anudip.app.dao.SubjectDAO;
import com.anudip.app.dao.UserDAO;
import com.anudip.app.entities.AppliedStudent;
import com.anudip.app.entities.Course;
import com.anudip.app.entities.EnrolledStudent;
import com.anudip.app.entities.Lecturer;
import com.anudip.app.entities.Subject;
import com.anudip.app.entities.User;
import com.anudip.app.entities.UserRole;

public class Main {
    public static void main(String[] args) {
    	Logger hibernateLogger = Logger.getLogger("org.hibernate");
        hibernateLogger.setLevel(Level.SEVERE);
        
        Scanner scanner = new Scanner(System.in);

        System.out.println("╔═╗┌┬┐┬ ┬┌┬┐┌─┐┌┐┌┌┬┐  ╔═╗┌┐┌┬─┐┌─┐┬  ┬  ┌┬┐┌─┐┌┐┌┌┬┐  ╔═╗┬ ┬┌─┐┌┬┐┌─┐┌┬┐");
        System.out.println("╚═╗ │ │ │ ││├┤ │││ │   ║╣ │││├┬┘│ ││  │  │││├┤ │││ │   ╚═╗└┬┘└─┐ │ ├┤ │││");
        System.out.println("╚═╝ ┴ └─┘─┴┘└─┘┘└┘ ┴   ╚═╝┘└┘┴└─└─┘┴─┘┴─┘┴ ┴└─┘┘└┘ ┴   ╚═╝ ┴ └─┘ ┴ └─┘┴ ┴");
        System.out.println("---------------------------------------------------------------------------");
        System.out.println("Welcome to the Student Enrollment System!");

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence.xml");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        // Create DAO instances
        AppliedStudentDAO appliedStudentDAO = new AppliedStudentDAO(entityManager);
        CourseDAO courseDAO = new CourseDAO(entityManager);
        EnrolledStudentDAO enrolledStudentDAO = new EnrolledStudentDAO(entityManager);
        SubjectDAO subjectDAO = new SubjectDAO(entityManager);
        LecturerDAO lecturerDAO = new LecturerDAO(entityManager);
        UserDAO userDAO = new UserDAO();
        
        
        //Populating Lecturer
        Lecturer lecturerBCA = new Lecturer("Sumitra Sir");
        lecturerDAO.create(lecturerBCA);
        Lecturer lecturerBTech = new Lecturer("Shah Rukh Sir");
        lecturerDAO.create(lecturerBTech);
        
        
         //Populating subjects
	     // BCA SUBJECTS---------------------------------------
	     Subject bcaSub_1 = new Subject("C++");
	     bcaSub_1.setLecturer(lecturerBCA);
	     subjectDAO.create(bcaSub_1);
	
	     Subject bcaSub_2 = new Subject("JAVA");
	     bcaSub_2.setLecturer(lecturerBCA); // Set lecturer for bcaSub_2
	     subjectDAO.create(bcaSub_2);
	
	     // BTECH SUBJECTS-------------------------------------
	     Subject btechSub_1 = new Subject("Maths");
	     btechSub_1.setLecturer(lecturerBTech); 
	     subjectDAO.create(btechSub_1);
	
	     Subject btechSub_2 = new Subject("Python");
	     btechSub_2.setLecturer(lecturerBTech); 
	     subjectDAO.create(btechSub_2);
     
        
        //Populating courses for Course table
        Course BCA = new Course("BCA",75);
       
        BCA.addSubject(bcaSub_1);
        BCA.addSubject(bcaSub_2);
        
        Course BTech = new Course("BTech",75);
        BTech.addSubject(btechSub_1);
        BTech.addSubject(btechSub_2);
        
        
        courseDAO.create(BCA);
        courseDAO.create(BTech);
        
        
        //creating ADMIN for login purpose [username: admin -> pass: root]
        User admin = new User("root","root","root@123",UserRole.ADMIN);
        userDAO.registerUserDetails(admin);
        
        //creating test USER for login purpose 
        User newUser = new User("user","user","user@123",UserRole.USER);
        userDAO.registerUserDetails(newUser);
        
        //REG Menu
        User loggedUser = registrationMenu(scanner, userDAO,entityManager);
        //LOGIN Menu
        
        System.out.println("BAck to main after login");

        // Login + Authentication
        if (loggedUser != null) {
        	System.out.println("\nLogin successful!");
        	System.out.println("Welcome, " + loggedUser.getUsername() + "!");
        	System.out.println();
            // Check user role
            if (loggedUser.getRole() == UserRole.ADMIN) {
                adminMenu(scanner, courseDAO, enrolledStudentDAO, entityManager);
            } else {
            	userMenu(scanner, loggedUser, courseDAO, enrolledStudentDAO, subjectDAO, lecturerDAO, appliedStudentDAO, entityManager);
            }
        } else {
        	
            System.out.println("Login failed. Exiting... USER ->"+loggedUser);
        }

        // Close EntityManager and EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
        scanner.close();
    }
    
    public static User loginMenu(Scanner scanner, UserDAO userDAO, EntityManager entityManager) {

    	User user = new User();

        System.out.println("\n*********************************");
        System.out.println("*            Login              *");
        System.out.println("*********************************");

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        while (username.trim().isEmpty()) {
            System.out.print("Username cannot be empty. Enter your username: ");
            username = scanner.nextLine();
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        

        // Perform login logic here (e.g., validate user credentials)
        user = userDAO.authenticateUser(username, password);
        return user;
      
    }
    
    public static User registrationMenu(Scanner scanner, UserDAO userDAO, EntityManager entityManager) {
    	
        
        int choice;
        
        	
            do {
            	
            	System.out.println("******************************");
                System.out.println("*     Registration Menu      *");
                System.out.println("******************************");  //add admin login
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
            	
            	switch (choice) {
                case 1:
                    registerUser(scanner, userDAO,entityManager);
                    break;
                case 2:
                	System.out.println("Getting you to login page....");
                	return loginMenu(scanner, userDAO, entityManager);
                case 3:
                	System.out.println("\nExiting...");
                	System.exit(0);
                default:
                    System.out.println("\nInvalid choice! Please enter a valid option.\n");
            }
            }while(choice != 3);
			return null;
            
    }


	public static void registerUser(Scanner scanner, UserDAO userDAO, EntityManager entityManager) {

		System.out.println("\n******************************");
        System.out.println("*       Registration         *");
        System.out.println("******************************");

        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        while (username.trim().isEmpty()) {
            System.out.print("Username cannot be empty. Enter your username: ");
            username = scanner.nextLine();
            scanner.nextLine(); // Consume newline character
        }

        System.out.print("Enter your email: ");
        String email = scanner.nextLine();
        
        while (email.trim().isEmpty()) {
            System.out.print("Email cannot be empty. Enter your Email: ");
            email = scanner.nextLine();
        }

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        scanner.nextLine(); // Consume newline character

        // Perform registration logic here (e.g., save user data to database)
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setEmail(email);
        newUser.setRole(UserRole.USER);
        
        userDAO.registerUserDetails(newUser);
        
        System.out.println("\nUser registered successfully!");
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println();
	}


    public static void adminMenu(Scanner scanner, CourseDAO courseDAO, EnrolledStudentDAO enrolledStudentDAO,EntityManager entityManager) {
        int choice;
        do {
        	System.out.println("\n*********************************");
            System.out.println("*          Admin Menu           *");
            System.out.println("*********************************");
            System.out.println("1. Create Course");
            System.out.println("2. View Courses");
            System.out.println("3. Delete Course");
            System.out.println("4. View Enrolled Students");
            System.out.println("5. Delete Enrolled Student");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createCourse(scanner, courseDAO);
                    break;
                case 2:
                    viewCourses(courseDAO);
                    break;
                case 3:
                    deleteCourse(scanner, courseDAO);
                    break;
                case 4:
                    viewEnrolledStudents(enrolledStudentDAO);
                    break;
                case 5:
                    deleteEnrolledStudent(scanner, enrolledStudentDAO);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 6);
    }

    public static void userMenu(Scanner scanner, User loggedUser, CourseDAO courseDAO, EnrolledStudentDAO enrolledStudentDAO, SubjectDAO subjectDAO, LecturerDAO lecturerDAO,AppliedStudentDAO appliedStudentDAO, EntityManager entityManager) {
        int choice;
        User appliedUser = new User();
        do {
        	System.out.println("\n********************************");
            System.out.println("*         User Menu            *");
            System.out.println("********************************");
            System.out.println("1. View Courses");
            System.out.println("2. Apply for Course");
            System.out.println("3. View Enrolled Students");
            System.out.println("4. View Subjects");
            System.out.println("5. View Teachers");
            System.out.println("6. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    viewCourses(courseDAO);
                    break;
                case 2:
                	userApplyingForCourse(scanner, appliedStudentDAO, courseDAO, enrolledStudentDAO, entityManager);
                    break;
                case 3:
                	viewEnrolledStudents(enrolledStudentDAO);
                    break;
                case 4:
                	viewSubjects(entityManager.find(Course.class, 1L),subjectDAO,courseDAO);
                    break;
                case 5:
                	viewTeachers(lecturerDAO);
                	break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }
        } while (choice != 6);
    }

    
    private static void viewSubjects(Course course, SubjectDAO subjectDAO, CourseDAO courseDAO) {
        List<Subject> subjects = courseDAO.getSubjectsInCourse(course);
        
        if (subjects.size() > 0) {
            System.out.println("*******************************************");
            System.out.println("*       Subjects in " + course.getCourseName() + "        *");
            System.out.println("*******************************************");
            System.out.printf("* %-30s | %-20s *\n", "Subject Name", "Lecturer");
            System.out.println("*******************************************");
            for (Subject subject : subjects) {
                System.out.printf("* %-30s | %-20s *\n", subject.getSubName(), subject.getLecturer().getLecturerName());
            }
            System.out.println("*******************************************");
        } else {
            System.out.println("No subjects found for " + course.getCourseName());
        }
    }

    private static void viewTeachers(LecturerDAO lecturerDAO) {
    	//if enrolled show teachers otherwise -> First get enrolled to get teacher list
        System.out.println("\nAll Teachers:");
        List<Lecturer> allTeachers = lecturerDAO.getAllTeachers();
        for (Lecturer teacher : allTeachers) {
            System.out.println(teacher);
        }
    }

    public static void createCourse(Scanner scanner, CourseDAO courseDAO) {
        Course course = new Course();
        System.out.println("Enter Course Details:");
        System.out.print("Course Name: ");
        course.setCourseName(scanner.nextLine());
        System.out.print("Course cutOff: ");
        course.setCutOffPercentage(scanner.nextInt());
        courseDAO.create(course);
        System.out.println("Course created successfully!");
    }
    
    public static void viewCourses(CourseDAO courseDAO) {
        System.out.println("\n************************************");
        System.out.println("*         View Courses             *");
        System.out.println("************************************");
        List<Course> courses = courseDAO.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            System.out.println("List of available courses:");
            for (int i = 0; i < courses.size(); i++) {
                Course course = courses.get(i);
//                System.out.println((i + 1) + ". Course Id ["+course.getCourseId() +"] "+ course.getCourseName() +" | CutOff = "+course.getCutOffPercentage()+"% |");
                System.out.printf("* %d. Course Id :%d | %-10s | CutOff : %d%% |\n", (i + 1), course.getCourseId(), course.getCourseName(), course.getCutOffPercentage());
            }
        }
    }

    public static void deleteCourse(Scanner scanner, CourseDAO courseDAO) {
        System.out.print("Enter Course ID to delete: ");
        long courseId = scanner.nextLong();
        courseDAO.delete(courseId);
        System.out.println("Course deleted successfully!");
    }

    
    public static void createEnrolledStudent(AppliedStudent appliedStudent, EnrolledStudentDAO enrolledStudentDAO, EntityManager entityManager) {
        EnrolledStudent enrolledStudent = new EnrolledStudent();
        enrolledStudent.setStudentName(appliedStudent.getName());
        enrolledStudent.setStudentId(appliedStudent.getStudentId());
        Course course = null;

        try {
            course = entityManager.find(Course.class, appliedStudent.getCourseId());
            if (course != null) {
                enrolledStudent.setCourse(course);
            } else {
                System.out.println("Invalid CourseId selected -> select only from available list.");
                return;
            }
        } catch (NullPointerException e) {
            System.out.println("Invalid CourseId selected -> select only from available list.");
            return;
        }

        // Instead of persisting, merge the entity to make it managed
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            enrolledStudent = entityManager.merge(enrolledStudent); // Merge the detached entity
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Exception while enrolling- " + e.getMessage());
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        // Now the entity is managed, proceed with any necessary operations
        enrolledStudentDAO.create(enrolledStudent);
        System.out.println("Enrolled student created successfully!");
    }
    
    public static void viewEnrolledStudents(EnrolledStudentDAO enrolledStudentDAO) {
        List<EnrolledStudent> enrolledStudents = enrolledStudentDAO.getAllEnrolledStudents();
        if (enrolledStudents.size() > 0) {
            System.out.println("*******************************************");
            System.out.println("*        Viewing Enrolled Students         *");
            System.out.println("*******************************************");
            System.out.printf("* Total Enrolled Students: %-16d *\n", enrolledStudents.size());
            System.out.println("*******************************************");
            System.out.println("*   ID    |   Student Name    |   Course   *");
            System.out.println("*******************************************");
            int count = 0;
            for (EnrolledStudent enrolledStudent : enrolledStudents) {
                System.out.printf("* %6d | %-17s | %-10s *\n", ++count, enrolledStudent.getStudentName(), enrolledStudent.getCourse().getCourseName());
            }
            System.out.println("*******************************************");
        } else {
            System.out.println("No Enrolled Students yet ..");
        }
    }


    public static void deleteEnrolledStudent(Scanner scanner, EnrolledStudentDAO enrolledStudentDAO) {
        System.out.print("Enter Enrolled Student ID to delete: ");
        long enrolledStudentId = scanner.nextLong();
        enrolledStudentDAO.delete(enrolledStudentId); // Corrected variable name
        System.out.println("Enrolled student deleted successfully!");
    }
    
    public static void userApplyingForCourse(Scanner scanner, AppliedStudentDAO appliedStudentDAO, CourseDAO courseDAO, EnrolledStudentDAO enrolledStudentDAO, EntityManager entityManager) {
        Long courseId = null;
        int cutOff;
        boolean status;

        System.out.println("\n************************************");
        System.out.println("*    Apply for Course             *");
        System.out.println("************************************");

        System.out.println("Enter Your Name:");
        String name = scanner.nextLine();
        System.out.println("Enter Your Address:");
        String address = scanner.nextLine();
        System.out.println("Enter Your Phone Num:");
        String phNum = scanner.nextLine();
        System.out.println("Enter Your Date of Birth:");
        String dob = scanner.nextLine();
        System.out.println("Enter Your Aggregate Percentage:");
        Long aggregatePercentage = scanner.nextLong();

        try {
            System.out.println("Enter Course ID you are applying for:");
            courseId = scanner.nextLong();

            cutOff = courseDAO.findById(courseId).getCutOffPercentage();
            status = aggregatePercentage >= cutOff;
        } catch (NullPointerException e) {
            System.out.println("Course ID: " + courseId + ", not available.");
            return;
        }

        AppliedStudent appliedStudent = new AppliedStudent(name, address, phNum, dob, courseId, aggregatePercentage, status);
        appliedStudentDAO.create(appliedStudent);

        if (appliedStudent.getStatus()) {
            System.out.println("You are eligible to get enrolled!");
            System.out.println("Press 'Y' for yes (or any key for no) -> ");
            if (scanner.next().equals("Y")) {
                createEnrolledStudent(appliedStudent, enrolledStudentDAO, entityManager);
            } else {
                System.out.println("Ok! getting you back to the main menu.");
            }
        } else {
            System.out.println("\nNot eligible for Enrollment -> aggregate% < " + cutOff + "%");
        }
        
    }

}


