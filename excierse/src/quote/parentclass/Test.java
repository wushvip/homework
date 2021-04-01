package quote.parentclass;

import quote.subclass.Student;
import quote.subclass.Teacher;

/**
 * @Author: wushuai
 * @Date: ${date} ${time}
 * @Description:
 */
public class Test {

    public static void main(String[] args) {
        Test  t1 = new Test();
        t1.test();
    }

    public void test(){
        int a = 10;
        TeacherAndStudent all = new TeacherAndStudent();
        System.out.println(all.getClass());
        all.setStudent(new Student(2,"jim"));
        newTeacher(all);
        changeStudentId(all);
        changeA(a);
        System.out.println("test a:" + a);
        System.out.println(all.getTeacher().getId());
        System.out.println(all.getStudent().getId() + all.getStudent().getName());
    }

    private void changeA(int a) {
        a++;
        System.out.println("after change a:" + a);
    }

    public static void changeStudentId(TeacherAndStudent all) {
        Student student = all.getStudent();
        student.setId(6);
        student.setName("kitt");
        all.setStudent(student);
    }

    private void newTeacher(TeacherAndStudent all) {
        System.out.println(all.getClass());
        all.setTeacher(new Teacher(1,"teacher wu"));
    }

    class TeacherAndStudent{
        private Teacher teacher;

        private Student student;

        public Teacher getTeacher() {
            return teacher;
        }

        public void setTeacher(Teacher teacher) {
            this.teacher = teacher;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
    }
}
