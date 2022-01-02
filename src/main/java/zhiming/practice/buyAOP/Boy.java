package zhiming.practice.buyAOP;

import org.springframework.stereotype.Component;

@Component
public class Boy implements IBuy{
    @Override
    public String buy(double price) {
        /*System.out.println("男孩买了一个游戏机");*/
        System.out.println(String.format("男孩花了%s元买了一个游戏机", price));
        return "游戏机";
    }
}
