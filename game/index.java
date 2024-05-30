class Index {
    public static void main(String[] args) {

        Hero matt = new Hero("Matt", 1, 20, 20, 0, 10);
        
        System.out.println(matt.toString());

        matt.levelUp();
        matt.levelUp();
        matt.levelUp();
        matt.levelUp();
        matt.levelUp();

        System.out.println(matt.toString());

    }
}

