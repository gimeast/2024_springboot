package hello.core.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    /**
     * @Method         : order
     * @Description    : stateful하게 만든 경우
     * @Author         : donguk
     * @Date           : 2024. 01. 01.
     * @param          : name
     * @param          : price
     * @return         : 
     */
    public void statefulOrder(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제!
    }

    /**
     * @Method         : order
     * @Description    : stateless하게 만든 경우
     * @Author         : donguk
     * @Date           : 2024. 01. 01.
     * @param          : name
     * @param          : price
     * @return         : price
     */
    public int statelessOrder(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        return price;
    }

    public int getPrice() {
        return price;
    }
}
