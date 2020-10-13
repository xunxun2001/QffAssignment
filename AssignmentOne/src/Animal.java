import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class Animal {
    private int age;
    private String type = new String();
    private String gender = new String();

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public void setGender(String gender) {
        this.gender = gender;
    }

    //注意String类的对比，不是常量池不能用'=='
    public boolean equal(Animal CompareAnimal){
        if(this.getAge()==CompareAnimal.getAge()&& Objects.equals(this.getGender(), CompareAnimal.getGender()) && Objects.equals(this.getType(), CompareAnimal.getType())) return true;
        else return  false;
    }
}
