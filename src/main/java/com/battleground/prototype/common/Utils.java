package com.battleground.prototype.common;

/**
 * Created by sugin on 15. 8. 7..
 */
public class Utils {
    public static String getKoreanLocationName(String location){
        switch (location){
            //상위 locations
            case "cityhall" :
                return "마을회관";
            case "square" :
                return "광장";
            case "dungeon" :
                return "던전";
            case "market" :
                return "시장";
            case "character" :
                return "캐릭터";
            //하위 locations
            case "everyone" :
                return "모두의 광장";
            case "meister" :
                return "장인의 집";
            case "tavern" :
                return "선술집";
            case "butcherscut" :
                return "푸줏간";
            case "backstreet" :
                return "쓸쓸한 뒷골목";
            default:
                break;
        }
        return "";
    }
}
