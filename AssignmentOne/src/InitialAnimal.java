import java.util.*;

public class InitialAnimal {
    ArrayList<Animal> MyAnimal=new ArrayList<Animal>();
    public static void main(String[] args) {
        ArrayList<Animal> MyAnimal=new ArrayList<Animal>();
        Animal CatOne=new Animal();
        Animal CatTwo=new Animal();
        CatOne.setGender("male"); CatOne.setType("cat"); CatOne.setAge(4);
        CatTwo.setGender("female"); CatTwo.setType("cat"); CatTwo.setAge(3);
        MyAnimal.add(CatOne);
        MyAnimal.add(CatTwo);
        Scanner in = new Scanner(System.in);
        getHelp();
        while(in.hasNext()){
            String Input=null;
            try {
                Input=in.next();
            }catch (Exception e){
                e.printStackTrace();
            }

            if(Objects.equals(Input, "help")){
                help();
            }
            if(Objects.equals(Input, "stop") || Objects.equals(Input, "0"))   break;
            if(Objects.equals(Input, "add")){
                Animal AddAnimal = new Animal();
                AddAnimal =setYourAnimal();
                MyAnimal.add(AddAnimal);
            }

            if(Objects.equals(Input, "remove")) {
                Animal RemoveInformation = new Animal();
                RemoveInformation=setYourAnimal();
                //System.out.println(RemoveInformation.getType());
                removeAnimals(MyAnimal, RemoveInformation);
            }
            if(Objects.equals(Input, "remove_by_number")) {
                System.out.println("请输入自然数");
                Scanner inPutANumber=new Scanner(System.in);
                int number=0;
                try{
                    number=inPutANumber.nextInt();
                }catch (Exception e){
                    e.printStackTrace();
                }

                removeAnimalsByNumber(MyAnimal,number);
            }

            if(Objects.equals(Input,"modify")){
                Animal ModifyInformation = new Animal();
                ModifyInformation=setYourAnimal();
                modifyAnimals(MyAnimal,ModifyInformation);
            }
            if(Objects.equals(Input,"modify_by_number")){
                System.out.println("请输入自然数");
                Scanner inPutANumber=new Scanner(System.in);
                int number=0;
                try {
                    number=inPutANumber.nextInt();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                modifyAnimalsByNumber(MyAnimal,number);
            }
            if(Objects.equals(Input,"sort")){
                sortedAnimals(MyAnimal);
            }
            if(Objects.equals(Input, "show")){
                showAnimals(MyAnimal);
            }

        }


    }
    public static Animal setYourAnimal(){
        Animal SetYourAnimal =new Animal();
        Scanner input=new Scanner(System.in);
        String information = input.nextLine();
        String[] split=information.split(" ");
        SetYourAnimal.setAge(Integer.parseInt(split[2]));
        SetYourAnimal.setGender(split[1]);
        SetYourAnimal.setType(split[0].toLowerCase());
        return SetYourAnimal;
    }

    public static void getHelp(){
        System.out.println("如果有疑问，请输入\"help\"获取本程序的使用说明");
    }
    public static void help(){
        System.out.println("输入格式为\"种类 性别（male or female） 年龄（整数,阿拉伯数字）\"");
        System.out.println("输入\"add\"添加元素");
        System.out.println("输入\"remove\"后，输入你想删除的信息，并在下一行进行删除");
        System.out.println("输入\"remove_by_number\"后，输入目前集合中你想删除的信息的序号（从1开始计数），并在下一行进行删除");
        System.out.println("输入\"modify\"后，输入你想修改的信息，并在下一行进行修改");
        System.out.println("输入\"modify_by_number\"后，输入目前集合中你想修改的信息的序号（从1开始计数），并在下一行进行修改");
        System.out.println("输入\"show\"显示目前集合中所有的信息");
        System.out.println("输入\"sort\"对年龄进行排序然后显示目前集合中所有的信息");
        System.out.println("输入\"0\"或者\"stop\"停止程序");
        System.out.println("=======================================================================");
    }
    public static void showAnimals(ArrayList<Animal> AnimalList){
        int len=AnimalList.size();
        Animal it=new Animal();
        for(int i=0;i<len;i++){
            it=AnimalList.get(i);
            System.out.println(it.getType()+" "+it.getGender()+" "+it.getAge());
        }
    }
    public static ArrayList<Animal> removeAnimals(ArrayList<Animal> AnimalList, Animal WantToDelete){
        Iterator it =AnimalList.iterator();
        while(it.hasNext()){
            //System.out.println("Has Next");
            Animal s=new Animal();
            s=(Animal)it.next();
            if(WantToDelete.equal(s)) {
                //System.out.println(true);
                it.remove();
            }
            else {
                continue;
            }

        }
        return AnimalList;

    }
    public static ArrayList<Animal> removeAnimalsByNumber(ArrayList<Animal> AnimalList,int number){
        AnimalList.remove(number-1);
        return AnimalList;
    }
    public static ArrayList<Animal> modifyAnimals(ArrayList<Animal> AnimalList,Animal WantToModify){
        int index=0;
        Iterator it =AnimalList.iterator();
        while(it.hasNext()){
            Animal s=new Animal();
            Animal m = new Animal();
            s=(Animal)it.next();
            if(WantToModify.equal(s)) {
                //System.out.println(true);
                m=setYourAnimal();
                AnimalList.set(index,m);
            }
            else {
                continue;
            }
            index+=1;
        }
        return AnimalList;
    }
    public static ArrayList<Animal> modifyAnimalsByNumber(ArrayList<Animal> AnimalList,int number){
        Animal m = new Animal();
        m=setYourAnimal();
        AnimalList.set(number-1,m);
        return AnimalList;
    }
    public static Comparator<Animal> c = new Comparator<Animal>() {
        @Override
        public int compare(Animal o1, Animal o2) {
            if((int)o1.getAge()<(int)o2.getAge())   return 1;
            else return -1;
        }
    };
    public static void sortedAnimals(ArrayList<Animal> AnimalList){
        AnimalList.sort(c);
        showAnimals(AnimalList);
    }

}
