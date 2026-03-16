package chstone12.UselessUsefulPluginDistr;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

import static chstone12.UselessUsefulPluginDistr.UselessUsefulPluginDistr.MARK_LETTERS;
//     public final static String MARK_LETTERS = "!? ㅋㅎ;.ㅜㅠ,><~-^@ㅇㄷ";


public class ChatModifier {


    public final static List<String> availableModifierCombinations = Arrays.asList("active_maid", "shy_maid", "aroused", "awkward", "sad");
    final static public List<String> availableModifiers = Arrays.asList("shuffle", "shorten", "combine", "heart", "mute", "undo", "dot", "morning", "master", "nyan", "semicolon", "laugh", "cute", "serious", "cry", "obfuscate", "chosung", "stammer");
    static public final List<String> availableUnmodifiers = Arrays.asList("shuffle", "shorten", "all", "heart", "mute", "dot", "morning", "master", "nyan", "semicolon", "laugh", "cute", "serious", "cry", "obfuscate", "chosung", "stammer");


    private String chatInput;

    public ChatModifier(String message) {
        chatInput = message;
    }

    public String getModifiedMessage() {
        return chatInput.replace("♡", "\uD83E\uDD0D").replace("  ", " ");
    }



    public String autoModify(List<String> modifiers) {

        boolean muted = false;

        if(modifiers.contains("mute")) {
            mute();
            muted = true;
        }

        if(!muted) {
            if (modifiers.contains("shorten")) shorten();
            if (modifiers.contains("shuffle")) shuffle();
            if (modifiers.contains("master")) toMaidsTalk();
            if (modifiers.contains("stammer")) toStammeringTalk();
            if (modifiers.contains("nyan")) toCatsTalk();
            if (modifiers.contains("cute")) makeItCute();
            if (modifiers.contains("cry")) youAreUseless();
            if (modifiers.contains("laugh")) addHehe();
        }
        if (modifiers.contains("dot")) toHesitatingTalk();

        if(modifiers.contains("morning") && !modifiers.contains("obfuscate")) {
            if (muted) arouseAndMute();
            else toMoaningTalk();
        }
        if(!muted && modifiers.contains("semicolon")) addSemicolon();
        if(modifiers.contains("heart")) addHeart();
        if(!muted && modifiers.contains("serious")) makeItSerious();
        if(!muted && modifiers.contains("obfuscate")) {
            if(modifiers.contains("morning")) toGaggedMoaning();
            else gag();
        }

        if(modifiers.contains("chosung")) toChosung();
        return getModifiedMessage();
    }


    public String autoCombine(String combineType) {
        if(availableModifierCombinations.contains(combineType)) {
            switch (combineType) {
                case "shy_maid":
                    toMaidsTalk();
                    toStammeringTalk();
                    toHesitatingTalk();
                    return getModifiedMessage();
                case "aroused":
                    toStammeringTalk();
                    toHesitatingTalk();
                    toMoaningTalk();
                    addHeart();
                    return getModifiedMessage();
                case "awkward":
                    toStammeringTalk();
                    toHesitatingTalk();
                    addSemicolon();
                    return getModifiedMessage();
                case "sad":
                    toStammeringTalk();
                    youAreUseless();
                    toHesitatingTalk();
                    return getModifiedMessage();
                case "active_maid":
                    toMaidsTalk();
                    makeItCute();
                    addHehe();
                    toMoaningTalk();
                    addHeart();
                    return getModifiedMessage();
                default:
                    return "도달할리가 없는 예외처리";
            }
        }

        else return "잘못된 변경 조합입니다.";
    }





    public void shorten() {
        String[] mark = splitLastMarkLetters(chatInput);

        String[] a = mark[0].split(" ");
        StringBuilder sb = new StringBuilder();
        for(String s : a) {
            try {
                sb.append(s.charAt(0));
            } catch (Exception ignored) {}
        }
        chatInput = sb + mark[1];
    }




    public void shuffle() {
        List<String> split = Arrays.asList(chatInput.split(""));
        Collections.shuffle(split);
        StringBuilder sb = new StringBuilder();
        for(String s : split) sb.append(s);
        chatInput = sb.toString();
    }

    public void youAreUseless() {
        StringBuilder sb = new StringBuilder();
        String[] t = chatInput.split("");
        for(String s : t) {
            if(Math.random() > 0.9) sb.append(getRandomRealDots()).append(s);
            else sb.append(s);
        }
        sb.append(".").append(getRandomRealDots()).append(getRandomCrying());
        if(Math.random() > 0.5) {
            chatInput = getRandomRealDots() + " " + sb;
        }
        else {
            if(Math.random() > 0.5) chatInput = "흐윽" + getRandomRealDots() + " " + sb;
            else chatInput = "흑." + getRandomRealDots() + " " + sb;
        }
    }

    public void makeItSerious() {
        String[] t = chatInput.split("");
        StringBuilder sb = new StringBuilder();
        for(String s : t) {
            if(!"!?ㅋㅎ;.ㅜㅠ,><~-^@ㅇㄷ".contains(s)) sb.append(s);
        }
        chatInput = sb + ".";
    }

    public void addHeart() {
        chatInput += " ♡";
    }


    public void mute() {
        int r = (int) (Math.random() * 5) + 1;
        int s = (int) (Math.random() * 5) + 1;
        String mute = "";
        switch (r) {
            case 1 -> mute += "으";
            case 2 -> mute += "으으";
            case 3 -> mute += "으으으";
            case 4 -> mute += "으으으으";
            default -> mute += "으으으으으";
        }
        switch (s) {
            case 1 -> mute += "....";
            case 2 -> mute += ".....";
            case 3 -> mute += "......";
            case 4 -> mute += ".......";
            default -> mute += "...";
        }
        chatInput = mute;
    }


    public void addHehe() {
        if(Math.random() > 0.5) chatInput += " ㅎ";
        else chatInput += " ㅎㅎ";
    }

    public void toHesitatingTalk() {
        String[] arr = chatInput.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String s : arr) {
            if(Math.random() > 0.55) {
                sb.append(s).append(" ");
                continue;
            }

            double r = Math.random() * 3d + 2.0d;
            int len = s.length();
            int r2 = (int) (Math.random() * 3);
            String dot;
            if(r2 == 0) dot = ".."; else if(r2 == 1) dot = "..."; else dot = "....";

            if(len > r) {
                int r3 = (int) (Math.random() * 3);
                String dot2;
                if(r3 == 0) dot2 = "..";
                else if(r3 == 1) dot2 = "...";
                else dot2 = "....";
                sb.append(s, 0, len/2).append(dot).append(s.substring(len/2)).append(dot2).append(" ");
            }
            else sb.append(s).append(dot).append(" ");
        }
        chatInput = sb.toString();
        if(chatInput.endsWith(" ")) chatInput = chatInput.substring(0, chatInput.length()-1);
        if(!chatInput.endsWith("...")) chatInput += "...";

        chatInput = chatInput.replace(",....", "....,").replace(",...", "...,").replace(",..", "..,");
    }



    public void toMaidsTalk() {

        switch (chatInput) {
            case "ㅈㅅ", "죄송" -> {
                chatInput = "주인님 죄송해요";
                return;
            }
            case "네", "ㅔ", "ㅖ", "ㅇㅇ", "ㅇ", "ㅔㅔ", "넹" -> {
                chatInput = "네 주인님!";
                return;
            }
            case "왜", "왜요", "why", "왜용", "왜영" -> {
                chatInput = "무슨 일이신가요 주인님!?";
                return;
            }
            case "바부", "바보" -> {
                chatInput = "주인님 바부..";
                return;
            }
            case "허접", "허접이래요", "허접..", "허접..." -> {
                chatInput = "주인님 허접.. ♡";
                return;
            }
        }

        String[] split = splitLastMarkLetters(chatInput);
        String inputCopy = split[0];
        String marks = split[1];

        if(inputCopy.equals("")) {
            chatInput = marks;
            return;
        }
        inputCopy = inputCopy.replace("살려줘", "벌해줘");
        inputCopy = inputCopy.replace("살려주", "벌해주");
        inputCopy = inputCopy.replace("살려달", "벌해달");

        inputCopy = inputCopy.replace("님", " 주인님");
        inputCopy = inputCopy.replace(" 아 주인님", " 아님");
        inputCopy = inputCopy.replace("주인 주인님", "주인님");
        inputCopy = inputCopy.replace("  ", " ");
        if(inputCopy.startsWith(" ")) inputCopy = inputCopy.substring(1);

        String lastTwoLetters = "a";
        if(inputCopy.length() >= 2) lastTwoLetters = inputCopy.substring(inputCopy.length()-2);
        boolean ignoreAndAddMaster = false;

        if(inputCopy.endsWith("합니다")) inputCopy = inputCopy.substring(0, inputCopy.length()-3) + "해요";
        else if(inputCopy.endsWith("됩니다")) inputCopy = inputCopy.substring(0, inputCopy.length()-3) + "돼요";
        else if(inputCopy.endsWith("십니까")) inputCopy = inputCopy.substring(0, inputCopy.length()-3) + "시나요";
        else if("돼용|되용|해용|돼영|돼여|되여|되영|해여|시져|시죵|어용|아용|어여|아여|게여|게용|게영|네용|네여|네영|".contains(lastTwoLetters)) {
            ignoreAndAddMaster = true;
        }
        else if(inputCopy.endsWith("줘") || inputCopy.endsWith("봐")) inputCopy += "요";

        String inputCopyDes = Normalizer.normalize(inputCopy, Normalizer.Form.NFD);

        if(inputCopyDes.endsWith("ᆻ다")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-2) + "어요";
        else if(inputCopyDes.endsWith("ᆻ음")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3) + "어요";
        else if(inputCopyDes.endsWith("ᆺ다")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-2) + "어요";
        else if(inputCopyDes.endsWith("ᆺ음")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3) + "어요";



        inputCopy = Normalizer.normalize(inputCopyDes, Normalizer.Form.NFC);


        String lastLetterAgain = inputCopy.substring(inputCopy.length()-1);

        if( (ignoreAndAddMaster || "요죠".contains(lastLetterAgain)) && !inputCopy.contains("주인님") ) {
            if(inputCopy.startsWith("네 ")) inputCopy = "네 주인님 " + inputCopy.substring(2);
            else inputCopy += " 주인님";
        }
        if(inputCopy.endsWith("나요")) inputCopy += "?";


        chatInput = inputCopy + marks;
    }




    public void toCatsTalk() {

        // 이퀄 먼저
        if(chatInput.length() == 1) {
            switch (chatInput) {
                case "왜" -> chatInput = "왜그러냥";
                case "뭐" -> chatInput = "뭐냥";
                case "머" -> chatInput = "머냥";
                case "!" -> chatInput = "냥!";
                case "?" -> chatInput = "냥?";
                case "ㅗ" -> chatInput = "죽으라냥";
                default -> chatInput = "냥";
            }
            return;
        }
        switch (chatInput) {
            case "안녕", "ㅎㅇ", "안뇽", "하이", "하이용", "안녕하세용", "안녕하세요", "hi" -> {
                chatInput = "안냥";
                return;
            }
            case "아니", "아닌데요", "아닌데", "아녀", "아니요", "아닌데용", "ㄴㄴ", "아닌디", "아뇨", "아니지", "아니용", "아뇽", "아닌뎅" -> {
                chatInput = "아니다냥";
                return;
            }
            case "ㅔㅔ", "그쵸", "그쳐", "ㅇㅇ", "그치", "그렇지", "그렇죠" -> {
                chatInput = "그렇다냥";
                return;
            }
            case "아잇", "아잇!", "아잇!!" -> {
                chatInput = "냥!!!!";
                return;
            }
            case "??" -> {
                chatInput = "냥??";
                return;
            }
            case "???", "????", "?????" -> {
                chatInput = "냐앙????";
                return;
            }
            case "??????", "???????", "????????", "?????????" -> {
                chatInput = "냐아앙?!?!???????";
                return;
            }
            case "ㅇㅎ", "아하" -> {
                chatInput = "냥!";
                return;
            }
            case "죽어", "죽으셈", "ㅗㅗ", "죽어!", "죽어!!", "죽어라", "죽으세요" -> {
                chatInput = "죽으라냥";
                return;
            }
            case "얼탱" -> {
                chatInput = "얼냥";
                return;
            }
            case "ㅇㅈ", "인정", "ㄹㅇ", "ㄹㅇㅋㅋ", "fd", "fdzz", "dw" -> {
                chatInput = "인정한다냥";
                return;
            }
            case "dz", "ㅇㅋ", "ok", "okay" -> {
                chatInput = "알겠다냥";
                return;
            }
            case "why", "Why" -> {
                chatInput = "왜그러냥";
                return;
            }
        }

        chatInput = chatInput.replace("냐", "냥");
        chatInput = chatInput.replace("주인님", "핈");
        chatInput = chatInput.replace("님", "냥");
        chatInput = chatInput.replace("핈", "주인님");
        chatInput = chatInput.replace("낭", "냥");
        chatInput = chatInput.replace("양", "냥");
        chatInput = chatInput.replace( "네 ", "냥 ");
        chatInput = chatInput.replaceAll("[녁-녛냑-냫]", "냥");

        // 여기까지 왔다면,
        String[] split = splitLastMarkLetters(chatInput);
        String inputCopy = split[0];
        String marks = split[1];

        if(inputCopy.equals("")) {
            chatInput = "냥" + marks;
            return;
        }

        // endsWith 또는 contains
        inputCopy = inputCopy.replace("심심해", "심심하다");
        inputCopy = inputCopy.replace("봐요", "보라냥");
        inputCopy = inputCopy.replace("줘요", "달라냥");
        inputCopy = inputCopy.replace("주세요", "달라냥");
        inputCopy = inputCopy.replace("주시죠", "달라냥");
        inputCopy = inputCopy.replace("보세요", "보라냥");
        inputCopy = inputCopy.replace("습니다", "다냥");
        inputCopy = inputCopy.replace("뭐죠", "뭐냥");
        inputCopy = inputCopy.replace("같다", "같다냥");
        inputCopy = inputCopy.replace("왜요", "왜냥");
        inputCopy = inputCopy.replace("하다", "하다냥");
        inputCopy = inputCopy.replace("싶어", "싶다냥");
        inputCopy = inputCopy.replace("듯", "냥");
        inputCopy = inputCopy.replace("밥", "사료");
        inputCopy = inputCopy.replace("아침", "사료");
        inputCopy = inputCopy.replace("점심", "사료");
        inputCopy = inputCopy.replace("저녁", "사료");
        inputCopy = inputCopy.replace("간식", "츄르");
        inputCopy = inputCopy.replace("야식", "츄르");
        inputCopy = inputCopy.replace("사료사료", "사료");
        inputCopy = inputCopy.replace("는데요", "다냥");
        inputCopy = inputCopy.replace("는데여", "다냥");
        inputCopy = inputCopy.replace("는데용", "다냥");
        inputCopy = inputCopy.replace("는데영", "다냥");
        inputCopy = inputCopy.replace("는데", "냥");
        inputCopy = inputCopy.replace("는다", "는다냥");
        inputCopy = inputCopy.replace("까요", "까냥");
        inputCopy = inputCopy.replace("까여", "까냥");
        inputCopy = inputCopy.replace("까용", "까냥");
        inputCopy = inputCopy.replace("까영", "까냥");

        if(inputCopy.endsWith("냥")) {
            chatInput = inputCopy + marks;
            return;
        }


        boolean yoChanged = false;


        String lastThreeLetters = "a";
        String lastTwoLetters = "a";
        String lastLetter = inputCopy.substring(inputCopy.length()-1);

        if(inputCopy.length() >= 3) lastThreeLetters = inputCopy.substring(inputCopy.length()-3);
        if(inputCopy.length() >= 2) lastTwoLetters = inputCopy.substring(inputCopy.length()-2);


        if(lastTwoLetters.equals("뭐야")) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "냥";
        else if(lastLetter.equals("죠")) {
            if(lastThreeLetters.equals("보시죠")) inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "보라냥";
            else {
                String temp = inputCopy.replace(" ","");
                if(temp.endsWith("뭐하시죠") || temp.endsWith("왜하시죠") || temp.endsWith("언제하시죠") || temp.endsWith("러시죠")) inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "냥";
                else if(lastTwoLetters.equals("시죠")) inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "냥";
                else if(lastTwoLetters.equals("거죠")) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "냥";
                else if(lastTwoLetters.equals("이죠")) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "냥";
                else if("야죠|하죠|되죠".contains(lastTwoLetters)) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "냥";
                else inputCopy = inputCopy.replace("죠", "냥");
            }
        }


        else if("요용여영".contains(lastLetter)) {
            if(lastThreeLetters.equals("러세요")) {
                inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "냥";
                yoChanged = true;
            }
            else if(lastTwoLetters.equals("래요")) {
                inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "러냥";
                yoChanged = true;
            }
            else if(lastTwoLetters.equals("세요")) {
                inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "라냥";
                yoChanged = true;
            }
            else if("에요|예요".contains(lastTwoLetters)) {
                inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "다냥";
                yoChanged = true;
            }
            else if("라구요|라고요|라구용|라고용|라구여|라구영|라고영|라고여".contains(lastThreeLetters)) {
                inputCopy = inputCopy.substring(0, inputCopy.length()-3) + "라냥";
                yoChanged = true;
            }
            else if("구요|고요|구용|고용|구영|구여|고용|고여".contains(lastTwoLetters)) {
                inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "냥";
                yoChanged = true;
            }
            else if("돼요|되요|돼용|되용|돼영|되영|돼여|되여".contains(lastTwoLetters)) {
                inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "된다냥";
                yoChanged = true;
            }
        }


        else if("아요|나요|나용|나여|나영|시져".contains(lastTwoLetters)) inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "냥";
        else if("라고|라구|뭐지|거야|이야|구나|하나|시나|려나".contains(lastTwoLetters)) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "냥";
        else if("음|야|지|나|니".contains(lastLetter)) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "냥";
        else if("네여|네요|네용|네영|군뇨|군영|군요|군용|군여|".contains(lastTwoLetters)) inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "다냥";
        else if("싫어요|싫어용|싫어여|싫어영|좋아요|좋아용|좋아여|좋아영".contains(lastThreeLetters)) inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "다냥";
        else if("자나|자냥".contains(lastTwoLetters)) inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "자냥";
        else if("거다|이다".contains(lastTwoLetters)) inputCopy += "냥";
        else if("거임|꺼임".contains(lastTwoLetters)) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "다냥";
        else if(lastTwoLetters.equals("버림")) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "렸다냥";
        else if(lastLetter.equals("셈")) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "라냥";
        else if(lastLetter.equals("됨")) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "된다냥";
        else if(lastLetter.equals("함")) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "하냥";
        else if(lastLetter.equals("군")) inputCopy = inputCopy.substring(0, inputCopy.length()-2) + "구냥";
        else if(lastLetter.equals("봐")) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "보라냥";
        else if(lastLetter.equals("줘")) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "달라냥";




        
        
        String inputCopyDes = Normalizer.normalize(inputCopy, Normalizer.Form.NFD);

        inputCopyDes = inputCopyDes.replace("ᆸ시다", "자냥");
        inputCopyDes = inputCopyDes.replace("ᆸ니다", "ᆫ다냥");
        inputCopyDes = inputCopyDes.replace("ᆹ다", "ᆹ다냥");
        inputCopyDes = inputCopyDes.replace("ᆻ다", "ᆻ다냥");
        inputCopyDes = inputCopyDes.replace("ᆺ다", "ᆺ다냥");
        inputCopyDes = inputCopyDes.replace("ᆹ음", "ᆹ다냥");
        inputCopyDes = inputCopyDes.replace("ᆻ음", "ᆻ다냥");
        inputCopyDes = inputCopyDes.replace("ᆺ음", "ᆺ다냥");
        inputCopyDes = inputCopyDes.replace("ᆹ어요", "ᆹ다냥");
        inputCopyDes = inputCopyDes.replace("ᆻ어요", "ᆻ다냥");
        inputCopyDes = inputCopyDes.replace("ᆺ어요", "ᆺ다냥");
        inputCopyDes = inputCopyDes.replace("ᆸ니까", "냥");
        inputCopyDes = inputCopyDes.replace("ᆫ데요", "다냥");
        inputCopyDes = inputCopyDes.replace("ᆫ데여", "다냥");
        inputCopyDes = inputCopyDes.replace("ᆫ데용", "다냥");
        inputCopyDes = inputCopyDes.replace("ᆫ데영", "다냥");
        inputCopyDes = inputCopyDes.replace("ᆫ요", "냥");
        inputCopyDes = inputCopyDes.replace("ᆫ가요", "냥");


        String lastThreeLettersAgain = "a";
        String lastFourLetters = "a";
        String lastSixLetters = "a";
        if(inputCopyDes.length() >= 3) lastThreeLettersAgain = inputCopyDes.substring(inputCopyDes.length()-3);
        if(inputCopyDes.length() >= 4) lastFourLetters = inputCopyDes.substring(inputCopyDes.length()-4);
        if(inputCopyDes.length() >= 6) lastSixLetters = inputCopyDes.substring(inputCopyDes.length()-6);

        if("ᆹ나|ᆹ지|ᆹ네".contains(lastThreeLetters)) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "ᆹ냥";
        else if("ᆻ나|ᆻ지|ᆻ네".contains(lastThreeLettersAgain)) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "ᆻ냥";
        else if("ᆺ나|ᆺ지|ᆺ네".contains(lastThreeLettersAgain)) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "ᆺ냥";
        else if("ᆫ데|ᆫ디|ᆫ가|ᆫ다".contains(lastThreeLettersAgain)) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "냥";
        else if("ᆯ꼐|ᆯ께".contains(lastThreeLettersAgain)) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "ᆯ께냥";
        else if("워요".equals(lastFourLetters)) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-4)  + "ᆸ다냥";
        else if("ᆯ래|ᆯ레".contains(lastThreeLettersAgain)) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-2) + "거다냥";
        else if(inputCopyDes.endsWith("ᆯ래요")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-4) + "거다냥";
        else if(inputCopyDes.endsWith("ᆯ레요")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-4) + "거다냥";
        else if(lastThreeLettersAgain.equals("ᆹ어")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "ᆹ다냥";
        else if(lastThreeLettersAgain.equals("ᅵ마")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "말라냥";
        else if(lastThreeLettersAgain.equals("ᆸ네")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "ᆸ냥";
        else if(lastThreeLettersAgain.equals("ᆯ게")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "ᆯ게냥";
        else if(lastThreeLettersAgain.equals("ᆻ어")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "ᆻ다냥";
        else if(lastThreeLettersAgain.equals("ᆺ어")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-3)  + "ᆺ다냥";
        else if(lastFourLetters.equals("ᆯ껄")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-4)  + "ᆯ꺼다냥";
        else if(lastFourLetters.equals("ᆫ듯")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-4)  + "ᆫ거같다냥";
        else if(lastFourLetters.equals("ᅥ럼")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-4)  + "렇냥";
        else if(lastFourLetters.equals("ᆯ걸")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-4)  + "ᆯ거다냥";
        else if(lastFourLetters.equals("ᅳ럼")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-4)  + "렇냥";
        else if(inputCopyDes.endsWith("ᅵ마요")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-5)  + "ᅵ말라냥";
        else if(lastSixLetters.equals("ᅵ마셈")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-6)  + "ᅵ말라냥";
        else if(lastSixLetters.equals("ᅵ말고")) inputCopyDes = inputCopyDes.substring(0, inputCopyDes.length()-6)  + "말라냥";


        inputCopy = Normalizer.normalize(inputCopyDes, Normalizer.Form.NFC);


        if(inputCopy.endsWith("네")) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "냥";
        if(inputCopy.endsWith("다")) inputCopy += "냥";
        if(!yoChanged && chatInput.endsWith("요")) inputCopy += "냥";
        if(!inputCopy.endsWith("냥")) {
            if(Math.random() > 0.5) inputCopy = inputCopy.substring(0, inputCopy.length()-1) + "냥";
            else inputCopy += "냥";
        }
        chatInput =  inputCopy.replace("냥냥", "냥") + marks;
    }


    public void makeItCute() {

        boolean shouldAddMaster = false;
        if(chatInput.endsWith("주인님")) {
            chatInput = chatInput.substring(0, chatInput.length()-3);
            shouldAddMaster = true;
        }

        String[] split = splitLastMarkLetters(chatInput);
        String inputCopy = split[0];
        String marks = split[1];

        String inputCopyDes = Normalizer.normalize(inputCopy, Normalizer.Form.NFD);
        String lastDes = "df";
        if(inputCopyDes.length() > 1) lastDes = inputCopyDes.substring(inputCopyDes.length()-1);
        if("ᅡᅣᅥᅧᅩᅭᅮᅵᅯᅫᅬᅪᅦᅢ".contains(lastDes)) inputCopyDes += "ᆼ";
        inputCopy = Normalizer.normalize(inputCopyDes, Normalizer.Form.NFC);


        chatInput = shouldAddMaster ? inputCopy + " 주인님 " + getRandomCuteThing() + getRandomCuteDots() + marks :  inputCopy + getRandomCuteThing() + getRandomCuteDots() + marks;
    }

    public void gag() {
        String inputDes = Normalizer.normalize(chatInput, Normalizer.Form.NFD);
        inputDes = inputDes.replace("ᆻᄋ", "ᄁ");
        inputDes = inputDes.replace("ᅰ","ᅦ");
        inputDes = inputDes.replace("ᅫ","ᅢ");
        inputDes = inputDes.replace("ᅪ","ᅡ");
        inputDes = inputDes.replace("ᅯ","ᅥ");
        inputDes = inputDes.replace("ᅧ","ᅥ");
        inputDes = inputDes.replace("ᅣ","ᅡ");
        inputDes = inputDes.replace("ᅴ","ᅵ");
        inputDes = inputDes.replace("ᅱ","ᅵ");
        inputDes = inputDes.replace("ᅬ","ᅢ");
        String[] t = inputDes.split("");


        StringBuilder sb = new StringBuilder();
        String s;
        int len = t.length;

        for(int i = 0; i < t.length; i++) {
            s = t[i];

            if("ᄀᄂᄃᄅᄌ".contains(s)) {
                sb.append("ᄋ");
            }
            else if("ᄇᄉᄎᄏᄐᄑᄁᄄᄈᄊᄍ".contains(s)) {
                sb.append("ᄒ");
            }


            else if("ᆮᆸᆺᆽᆾᆿᇀᇁᆼᇂᆩᆻ".contains(s)) {
                sb.append("ᆨ");
            }
            else if("ᆫᆯᆷᆱᆳᆴᆵᆶ".contains(s)) {
                sb.append("ᆼ");
            }
            else if("ᆩᆻᆪᆬᆭᆰᆲᆹ".contains(s)) {
                sb.append("ᆩ");
            }
            else {

                if(i != 0 && i < len-1) {
                    String pastChar = t[i-1];
                    String nextChar = t[i+1];

                    // ㄱ 다음에 자음이 또 있다면, 그 자음을 ㄱ 또는 ㄲ으로
                    if(s.equals("ᆨ") && "ᄀᄂᄃᄅᄆᄇᄉᄋᄌᄎᄏᄐᄑᄒᄁᄄᄈᄊᄍ".contains(nextChar)) {
                        if(Math.random() > 0.5) t[i+1] = "ᄀ";
                        else t[i+1] = "ᄁ";
                        sb.append(s);
                    }

                    // 모음 바로 뒤가 거센소리(ㅎ 제외)이고 그 거센소리 바로 다음에 또 모음이라면, 저 거센소리는 무조건 ㄲ으로
                    else if("ᄎᄏᄐᄑ".contains(s) && "ᅭᅧᅣᅢᅦᅵᅡᅥᅩᅮᅳᅲᅴᅱᅯᅰᅫᅬᅪᅤᅨ".contains(pastChar) && "ᅭᅧᅣᅢᅦᅵᅡᅥᅩᅮᅳᅲᅴᅱᅯᅰᅫᅬᅪᅤᅨ".contains(nextChar)) {
                        sb.append("ᄁ");
                    }


                    // 앞에 자음이 있을 때만  ᅭ -> ᅩ 그리고 ᅲ -> ᅮ 하기
                    else if("ᅭᅧᅣᅢᅦᅵᅡᅥᅩᅮᅳᅲᅴᅱᅯᅰᅫᅬᅪᅤᅨ".contains(pastChar)) {
                        if(s.equals("ᅭ")) sb.append("ᅩ");
                        else if(s.equals("ᅲ")) sb.append("ᅮ");
                        else sb.append(s);
                    }

                    else sb.append(s);
                }

                else sb.append(s);
            }
        }
        // ㄲㄲ -> ㄱㄲ
        // ㄱㄱ -> (초성) ㄲ
        String result = sb.toString().replace("ᆩᄁ", "ᆨᄁ").replace("ᆨᄀ", "ᄁ");

        chatInput = Normalizer.normalize(result, Normalizer.Form.NFC);


        if(Pattern.compile("[A-Za-z]").matcher(chatInput).find()) {
            obfuscateEnglish();
        }

    }



    public void toStammeringTalk() {
        String[] t = chatInput.split(" ");
        StringBuilder sb = new StringBuilder();


        String[] firstWord = Normalizer.normalize(String.valueOf(t[0].charAt(0)), Normalizer.Form.NFD).split("");
        String lastSymbolFromFirstWord = firstWord[firstWord.length-1];
        if("ᆨᆩᆻᆪᆬᆭᆰᆲᆹᆫᆯᆷᆱᆳᆴᆵᆶᆮᆸᆺᆽᆾᆿᇀᇁᆼᇂᆩᆻ".contains(lastSymbolFromFirstWord)) {
            String removeBatchim = Normalizer.normalize(firstWord[0] + firstWord[1], Normalizer.Form.NFC);
            sb.append(removeBatchim).append(", ").append(t[0]).append(" ");
        }
        else if("ᅭᅧᅣᅢᅦᅵᅡᅥᅩᅮᅳᅲᅴᅱᅯᅰᅫᅬᅪᅤᅨ".contains(lastSymbolFromFirstWord)) {
            String firstChosung = firstWord[0].replace("ᄀ", "ㄱ").replace("ᄂ", "ㄴ").replace("ᄃ", "ㄷ")
                    .replace("ᄅ", "ㄹ").replace("ᄆ", "ㅁ").replace("ᄇ", "ㅂ").replace("ᄉ", "ㅅ")
                    .replace("ᄋ", "ㅇ").replace("ᄌ", "ㅈ").replace("ᄎ", "ㅊ").replace("ᄏ", "ㅋ")
                    .replace("ᄐ", "ㅌ").replace("ᄑ", "ㅍ").replace("ᄒ", "ㅎ").replace("ᄁ", "ㄲ")
                    .replace("ᄄ", "ㄸ").replace("ᄈ", "ㅃ").replace("ᄊ", "ㅆ").replace("ᄍ", "ㅉ");

            sb.append(firstChosung).append(", ").append(t[0]).append(" ");
        }
        else sb.append(firstWord[0]).append(", ").append(t[0]).append(" ");

        if(t.length >= 2) {
            for(int i = 1; i < t.length; i++) {
                if(Math.random() > 0.65) {

                    String[] firstWord2 = Normalizer.normalize(String.valueOf(t[i].charAt(0)), Normalizer.Form.NFD).split("");
                    String lastSymbolFromFirstWord2 = firstWord2[firstWord2.length-1];
                    if("ᆨᆩᆪᆫᆬᆭᆮᆯᆰᆱᆲᆳᆴᆵᆶᆷᆸᆹᆺᆻᆼᆽᆾᆿᇀᇁᇂ".contains(lastSymbolFromFirstWord2)) {
                        String removeBatchim = Normalizer.normalize(firstWord2[0] + firstWord2[1], Normalizer.Form.NFC);
                        sb.append(removeBatchim).append(", ").append(t[i]).append(" ");
                    }
                    else if("ᅭᅧᅣᅢᅦᅵᅡᅥᅩᅮᅳᅲᅴᅱᅯᅰᅫᅬᅪᅤᅨ".contains(lastSymbolFromFirstWord2)) {
                        String firstChosung = firstWord2[0].replace("ᄀ", "ㄱ").replace("ᄂ", "ㄴ").replace("ᄃ", "ㄷ")
                                .replace("ᄅ", "ㄹ").replace("ᄆ", "ㅁ").replace("ᄇ", "ㅂ").replace("ᄉ", "ㅅ")
                                .replace("ᄋ", "ㅇ").replace("ᄌ", "ㅈ").replace("ᄎ", "ㅊ").replace("ᄏ", "ㅋ")
                                .replace("ᄐ", "ㅌ").replace("ᄑ", "ㅍ").replace("ᄒ", "ㅎ").replace("ᄁ", "ㄲ")
                                .replace("ᄄ", "ㄸ").replace("ᄈ", "ㅃ").replace("ᄊ", "ㅆ").replace("ᄍ", "ㅉ");

                        sb.append(firstChosung).append(", ").append(t[i]).append(" ");
                    }
                    else sb.append(firstWord2[0]).append(", ").append(t[i]).append(" ");


                }
                else sb.append(t[i]).append(" ");
            }
        }

        chatInput = sb.toString();
    }


    public void toChosung() {
        chatInput = chatInput.replaceAll("[가-깋]", "ㄱ").replaceAll("[나-닣]", "ㄴ")
                .replaceAll("[다-딯]", "ㄷ").replaceAll("[라-맇]", "ㄹ")
                .replaceAll("[마-밓]", "ㅁ").replaceAll("[바-빟]", "ㅂ")
                .replaceAll("[사-싷]", "ㅅ").replaceAll("[아-잏]", "ㅇ")
                .replaceAll("[자-짛]", "ㅈ").replaceAll("[차-칳]", "ㅊ")
                .replaceAll("[카-킿]", "ㅋ").replaceAll("[타-팋]", "ㅌ")
                .replaceAll("[파-핗]", "ㅍ").replaceAll("[하-힣]", "ㅎ")
                .replaceAll("[까-낗]", "ㄲ").replaceAll("[따-띻]", "ㄸ")
                .replaceAll("[빠-삫]", "ㅃ").replaceAll("[싸-앃]", "ㅆ")
                .replaceAll("[짜-찧]", "ㅉ")
                .replace("a", "").replace("e", "").replace("i", "")
                .replace("u", "").replace("o", "")
                .replace("A", "").replace("E", "").replace("I", "")
                .replace("U", "").replace("O", "");


    }




    public void toGaggedMoaning() {
        String inputDes = Normalizer.normalize(chatInput, Normalizer.Form.NFD);
        inputDes = inputDes.replace("ᆻᄋ", "ᄁ");
        inputDes = inputDes.replace("ᅰ","ᅦ");
        inputDes = inputDes.replace("ᅫ","ᅢ");
        inputDes = inputDes.replace("ᅪ","ᅡ");
        inputDes = inputDes.replace("ᅯ","ᅥ");
        inputDes = inputDes.replace("ᅧ","ᅥ");
        inputDes = inputDes.replace("ᅣ","ᅡ");
        inputDes = inputDes.replace("ᅴ","ᅵ");
        inputDes = inputDes.replace("ᅱ","ᅵ");
        inputDes = inputDes.replace("ᅬ","ᅢ");
        String[] t = inputDes.split("");

        StringBuilder sb = new StringBuilder();
        String s;
        int len = t.length;

        for(int i = 0; i < t.length; i++) {
            s = t[i];
            if("ᄀᄂᄃᄅᄌ".contains(s)) {
                sb.append("ᄋ");
            }
            else if("ᄇᄉᄎᄏᄐᄑᄁᄄᄈᄊᄍ".contains(s)) {
                sb.append("ᄒ");
            }
            else if("ᆮᆸᆺᆽᆾᆿᇀᇁᆼᇂᆩᆻ".contains(s)) {
                sb.append("ᆨ");
            }
            else if("ᆫᆯᆷᆱᆳᆴᆵᆶ".contains(s)) {
                sb.append("ᆼ");
            }
            else if("ᆩᆻᆪᆬᆭᆰᆲᆹ".contains(s)) {
                sb.append("ᆩ");
            }
            else {
                if(i != 0 && i < len-1) {
                    String pastChar = t[i-1];
                    String nextChar = t[i+1];
                    if(s.equals("ᆨ") && "ᄀᄂᄃᄅᄌᄇᄉᄒᄎᄋᄏᄐᄑᄁᄄᄈᄊᄍ".contains(nextChar)) {
                        if(Math.random() > 0.5) t[i+1] = "ᄀ";
                        else t[i+1] = "ᄁ";
                        sb.append(s);
                    }
                    else if("ᄎᄏᄐᄑ".contains(s) && "ᅭᅧᅣᅢᅦᅵᅡᅥᅩᅮᅳᅲᅴᅱᅯᅰᅫᅬᅪᅤᅨ".contains(pastChar) && "ᅭᅧᅣᅢᅦᅵᅡᅥᅩᅮᅳᅲᅴᅱᅯᅰᅫᅬᅪᅤᅨ".contains(nextChar)) {
                        sb.append("ᄁ");
                    }
                    else if("ᅭᅧᅣᅢᅦᅵᅡᅥᅩᅮᅳᅲᅴᅱᅯᅰᅫᅬᅪᅤᅨ".contains(pastChar)) {
                        if(s.equals("ᅭ")) sb.append("ᅩ");
                        else if(s.equals("ᅲ")) sb.append("ᅮ");
                        else sb.append(s);
                    }
                    else sb.append(s);
                }
                else sb.append(s);
            }
        }
        String[] result = sb.toString().replace("ᆩᄁ", "ᆨᄁ").replace("ᆨᄀ", "ᄁ").split("");
        StringBuilder sb2 = new StringBuilder();

        for(String ss : result) {
            if(Math.random() > 0.85) {
                if(ss.equals("ᄋ")) sb2.append("ᄒ");
                else if("ᆮᆸᆽᆾᆿᇀᇁᆼᇂᆩᆻᆩᆻᆪᆬᆭᆰᆲᆹ".contains(ss)) sb2.append("ᆺ");
                else sb2.append(ss);
            }
            else sb2.append(ss);
        }

        String[] beforeDots = Normalizer.normalize(sb2.toString(), Normalizer.Form.NFC).split("");
        StringBuilder sb3 = new StringBuilder();

        for(String sss : beforeDots) {
            if(Math.random() > 0.93) {
                if(Math.random() > 0.5) sb3.append(sss).append(getRandomDots()).append(" ").append(getRandomMoan()).append(getRandomDots()).append(" ");
                else sb3.append(sss).append(getRandomDots()).append(" ").append(getRandomMoan()).append(getRandomDots()).append("♡ ");
            }
            else sb3.append(sss);
        }

        if(Math.random() > 0.5) chatInput = getRandomMoan2() + getRandomDots() + " " + sb3 + getRandomDots();
        else chatInput = sb3 + getRandomDots() + " " + getRandomMoan2() + getRandomDots();



        if(Pattern.compile("[A-Za-z]").matcher(chatInput).find()) {
            obfuscateEnglish();
        }

    }





    public void toMoaningTalk() {
        chatInput = chatInput.replace("갔", "가버렸");
        chatInput = chatInput.replace("살려줘", "벌해줘");
        chatInput = chatInput.replace("살려주", "벌해주");
        chatInput = chatInput.replace("살려달", "벌해달");
        int len = chatInput.length();
        if(len > 9) {
            String[] tempChat = chatInput.split("");
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < len; i++) {
                if(i > 3 && i < len-3) {
                    if(Math.random() > 0.06) sb.append(tempChat[i]);
                    else {
                        String siot = tempChat[i];
                        String siotCopy = Normalizer.normalize(siot, Normalizer.Form.NFD);
                        String siotCopyCopy = siotCopy.substring(siotCopy.length()-1);
                        if("ᅡᅣᅥᅧᅩᅭᅮᅲᅳᅵᅴᅱᅰᅯᅫᅬᅪᅤᅨᅦᅢ".contains(siotCopyCopy)) siotCopy += "ᆺ";
                        siot = Normalizer.normalize(siotCopy, Normalizer.Form.NFC);

                        sb.append(siot).append(getRandomDots()).append(" ").append(getRandomMoan()).append(getRandomDots()).append(" ");
                    }
                }
                else sb.append(tempChat[i]);
            }
            chatInput = sb.toString();
        }

        if(Math.random() > 0.5) {

            String inputDes = Normalizer.normalize(chatInput, Normalizer.Form.NFD);
            String lastDes = inputDes.substring(inputDes.length()-1);
            if("ᅡᅣᅥᅧᅩᅭᅮᅲᅳᅵᅴᅱᅰᅯᅫᅬᅪᅤᅨᅦᅢ".contains(lastDes)) inputDes += "ᆺ";
            chatInput = Normalizer.normalize(inputDes, Normalizer.Form.NFC);

            chatInput = chatInput + getRandomDots() + " " + getRandomMoan() + getRandomDots();

        }
        else chatInput = getRandomMoan() + getRandomDots() + " " + chatInput + getRandomDots();

    }

    public void arouseAndMute() {
        int len = chatInput.length() / 10;
        if(len == 0) len = 1;
        if(Math.random() > 0.77) len++;
        chatInput = generateRandomMoan(len);
    }

    public void addSemicolon() {
        String[] arr = chatInput.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String s : arr) {
            if(Math.random() > 0.4) {
                String semicolons;
                int r = (int) (Math.random() * 3);
                if (r == 0) semicolons = ";"; else if (r == 1) semicolons = ";;"; else semicolons = ";;;";
                sb.append(s).append(semicolons).append(" ");
            }
            else sb.append(s).append(" ");
        }
        chatInput = sb.toString();
        if(!chatInput.replace(" ", "").endsWith(";")) chatInput = chatInput + " ;;";
    }


















    private static String getRandomDots() {
        int r = (int) (Math.random() * 5);
        return switch (r) {
            case 0 -> ",";
            case 2 -> "..,";
            case 3 -> ",,,";
            case 4 -> ".,,";
            default -> ",,";
        };
    }

    private static String getRandomMoan() {
        int r = (int) (Math.random() * 6);
        return switch (r) {
            case 0 -> "핫";
            case 1 -> "읏";
            case 2 -> "아읏";
            case 3 -> "으읏";
            case 4 -> "응앗";
            default -> "하읏";
        };
    }

    private static String getRandomMoan2() {
        int r = (int) (Math.random() * 20);
        return switch (r) {
            case 0 -> "우으..";
            case 1 -> "흐으..";
            case 2 -> "으으으..";
            case 3 -> "흐읏..";
            case 4 -> "으으읏..";
            case 5 -> "헤으응..";
            case 6 -> "으으..";
            case 7 -> "우으으..";
            case 8 -> "우으으으..";
            case 9 -> "흐으으..";
            case 10 -> "흐으으으";
            case 11 -> "흐으읏..";
            case 12 -> "흐으으읏..";
            case 13 -> "으읏..";
            case 14 -> "으으으읏..";
            case 15 -> "흐윽..";
            case 16 -> "으으으윽..";
            case 17 -> "으으으으..";
            case 18 -> "으으으으으..";
            case 19 -> "우으으으으..";
            default -> "하읏";
        };
    }

    private static String generateRandomMoan(int count) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < count; i++) {
            sb.append(getRandomMoan2()).append(getRandomDots()).append(" ");
        }
        return sb.toString();
    }


    private static String[] splitLastMarkLetters(String from) {
        StringBuilder marks = new StringBuilder();
        String inputCopy = from;

        String lastInputLetter = from.substring(from.length()-1);
        if(MARK_LETTERS.contains(lastInputLetter)) {
            for(int i = from.length()-1; i >= 0; i--) {
                char temp = from.charAt(i);
                if(MARK_LETTERS.contains(String.valueOf(temp))) {
                    marks.insert(0, temp);
                    inputCopy = inputCopy.substring(0, inputCopy.length()-1);
                }
                else break;
            }
        }

        return new String[]{inputCopy, marks.toString()};

    }


    private static String getRandomCuteThing() {
        int r = (int) (Math.random() * 8);
        return switch (r) {
            case 0 -> " 헤헤";
            case 1 -> " 헷";
            case 2 -> " ㅎㅎ";
            case 3 -> " >.<";
            case 4 -> " ㅋㅎㅎ";
            case 5 -> " ㅇㅅㅇ";
            case 6 -> " ㅋㅎ";
            case 7 -> " 헤헷";
            default -> null;
        };

    }

    private static String getRandomCuteDots() {
        int r = (int) (Math.random() * 6);
        return switch (r) {
            case 1 -> "..";
            case 2 -> "..,";
            case 3 -> "...";
            case 4 -> "...,";
            case 5 -> ",,";
            case 0 -> ",,,";
            default -> null;
        };

    }


    private static String getRandomRealDots() {
        int r = (int) (Math.random() * 4);
        return switch (r) {
            case 0 -> "..";
            case 1 -> "...";
            case 2 -> "....";
            case 3 -> ".....";
            default -> "...";
        };
    }

    private static String getRandomCrying() {
        int r = (int) (Math.random() * 7);
        return switch (r) {
            case 0 -> "ㅜㅜ";
            case 1 -> "ㅠㅠ";
            case 2 -> "ㅠ";
            case 3 -> "ㅠㅠㅠ";
            case 4 -> "ㅠㅠㅜㅠ";
            case 5 -> "ㅜㅜㅜ";
            case 6 -> "ㅠㅠㅠㅠ";
            default -> "...";
        };
    }

    private void obfuscateEnglish() {
        StringBuilder sb2 = new StringBuilder();
        String[] forEnglish = chatInput.toLowerCase().split("");

        for (String e : forEnglish) {

            if(!e.matches("[A-Za-z]")) {
                sb2.append(e);
                continue;
            }

            if ("aioeu".contains(e)) sb2.append(e);
            else {
                int r = (int) (Math.random() * 3);

                if(r==0) sb2.append("m");
                else if(r==1) sb2.append("p");
                else sb2.append("h");
            }


        }
        chatInput = sb2.toString();
    }


}




// ᆸ시다 ᆸ니다 ᆹ다 ᆻ다 ㅅ다 ᆹ음 ᆻ음 ᆹ어요 ᆻ어요 ᆺ어요 ᆸ니까 ᆫ데요 ᆫ데여 ᆫ데용 ᆫ데영 ᆫ요 ᆫ가요
// 자냥 ᆫ다냥 ᆹ다냥 ᆻ다냥 ᆺ다냥 ᆹ다냥 ᆻ다냥 ᆺ다냥 ᆹ다냥 ᆻ다냥 ᆺ다냥 냥 다냥 다냥 다냥 다냥 냥 냥

// ENDSWITH
// ㅄ나 -> ㅄ냥
// ㅆ나 -> ㅆ냥
// ㅅ나 -> ㅅ냥
// ㅄ지 -> ㅄ냥
// ㅆ지 -> ㅆ냥
// ㅅ지 -> ㅅ냥
// ㄴ데 -> 냥
// ㄴ디 -> 냥
// ㅅ어 -> ㅅ다냥
// ㅆ어 -> ㅆ다냥
// ㅄ어 -> ㅄ다냥
// ㅣ마 -> 말라냥
// ㅣ말고 -> 말라냥
// ㄴ가 -> 냥
// ㅓ럼 -> 렇냥
// ㅡ럼 -> 렇냥
// ㅅ네 -> ㅅ냥
// ㅆ네 -> ㅆ냥
// ㅂ네 -> ㅂ냥
// ㅄ네 -> ㅄ냥
// ㄴ다 -> 냥
// ㄹ걸 -> ㄹ거다냥
// ㄹ껄 -> ㄹ꺼다냥
// ㅣ마요 -> ㅣ말라냥
// ㅣ마셈 -> ㅣ말라냥
// ㄹ게 -> ㄹ게냥
// ㄹ께 -> ㄹ께냥
// ㄹ꼐 -> ㄹ께냥




// REPLACE
// ㅂ시다 -> 자냥
// ㅂ니다 -> ㄴ다냥
// ㅄ다 -> ㅄ다냥
// ㅆ다 -> ㅆ다냥
// ㅅ다 -> ㅅ다냥
// ㅄ음 -> ㅄ다냥
// ㅆ음 -> ㅆ다냥
// ㅅ음 -> ㅅ다냥
// ㅄ어요 -> ㅄ다냥
// ㅆ어요 -> ㅆ다냥
// ㅅ어요 -> ㅅ다냥
// ㅂ니까 -> 냥
// ㄴ데요 -> 다냥
// ㄴ데여 -> 다냥
// ㄴ데영 -> 다냥
// ㄴ데용 -> 다냥
// ㄴ요 -> 냥
// ㄴ가요 -> 냥
