package chstone12.UselessUsefulPluginDistr;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;

import static chstone12.UselessUsefulPluginDistr.ChatModifier.*;
import static chstone12.UselessUsefulPluginDistr.UselessUsefulPluginDistr.CHAT_MODIFY;
import static chstone12.UselessUsefulPluginDistr.UselessUsefulPluginDistr.PLAYER_LIST;

public class chat_modify implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args.length < 2) {
            sender.sendMessage("\u00A7c그렇게 쓰는 명령어 아닌데 ㅋㅋ");
            return false;
        }


        String modifier = args[1];
        String target = args[0];
        if(target.equals("@s")) target = sender.getName();


        List<String> targetsModifiers = CHAT_MODIFY.get(target);
        if(targetsModifiers == null) targetsModifiers = new ArrayList<>();


        if(!PLAYER_LIST.contains(target)) {
            sender.sendMessage("존재하지 않는 플레이어입니다. (대소문자도 맞춰야 됨)");
            return false;
        }


        switch (modifier) {
            case "mute" -> {
                if(targetsModifiers.contains("mute")) sender.sendMessage("\u00A7c해당 플레이어는 이미 말을 못합니다.");
                else {
                    targetsModifiers.add("mute");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "을(를) 말하지 못하게 만들었습니다.");
                }
            }
            case "shuffle" -> {
                if(targetsModifiers.contains("shuffle")) sender.sendMessage("\u00A7c해당 플레이어는 이미 채팅이 섞여서 쳐집니다.");
                else {
                    targetsModifiers.add("shuffle");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "의 채팅이 섞이도록 만들었습니다.");
                }
            }
            case "shorten" -> {
                if(targetsModifiers.contains("shorten")) sender.sendMessage("\u00A7c해당 플레이어는 이미 말이 짧습니다.");
                else {
                    targetsModifiers.add("shorten");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "의 말이 짧아집니다.");
                }
            }
            case "heart" -> {
                if(targetsModifiers.contains("heart")) sender.sendMessage("\u00A7c해당 플레이어는 이미 말 끝에 하트를 붙이고 있습니다.");
                else {
                    targetsModifiers.add("heart");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제 말 끝마다 하트를 붙입니다.");
                }
            }
            case "dot" -> {
                if(targetsModifiers.contains("dot")) sender.sendMessage("\u00A7c해당 플레이어는 이미 ...을 붙이고 있습니다.");
                else {
                    targetsModifiers.add("dot");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제 말 중간중간마다 ...이 붙습니다.");
                }
            }
            case "morning" -> {
                if(targetsModifiers.contains("morning")) sender.sendMessage("\u00A7c해당 플레이어는 이미.. 기분이 좋네요..!");
                else {
                    targetsModifiers.add("morning");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "의.. 기분이 좋아졌습니다!");
                }
            }
            case "master" -> {
                if(targetsModifiers.contains("master")) sender.sendMessage("\u00A7c해당 플레이어는 이미 주인님을 모시고 있습니다.");
                else {
                    targetsModifiers.add("master");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제 조건적으로 말 끝에 주인님이 붙습니다.");
                }
            }
            case "nyan" -> {
                if(targetsModifiers.contains("nyan")) sender.sendMessage("\u00A7c해당 플레이어는 이미 냥체를 쓰고 있습니다.");
                else {
                    targetsModifiers.add("nyan");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제 냥체를 사용합니다.");
                }
            }
            case "semicolon" -> {
                if(targetsModifiers.contains("semicolon")) sender.sendMessage("\u00A7c해당 플레이어는 이미 땀을 잔뜩 흘리고 있습니다.");
                else {
                    targetsModifiers.add("semicolon");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제 말 사이사이에 ;;이 붙습니다.");
                }
            }
            case "laugh" -> {
                if(targetsModifiers.contains("laugh")) sender.sendMessage("\u00A7c해당 플레이어는 이미 웃음이 많습니다.");
                else {
                    targetsModifiers.add("laugh");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제 말 끝에 ㅎㅎ이 붙습니다.");
                }
            }
            case "cute" -> {
                if(targetsModifiers.contains("cute")) sender.sendMessage("\u00A7c해당 플레이어는 이미 귀여워요.");
                else {
                    targetsModifiers.add("cute");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제 귀여운 말투를 씁니다.");
                }
            }
            case "serious" -> {
                if(targetsModifiers.contains("serious")) sender.sendMessage("\u00A7c해당 플레이어는 이미 진지합니다.");
                else {
                    targetsModifiers.add("serious");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제 진지해졌습니다.");
                }
            }
            case "cry" -> {
                if(targetsModifiers.contains("cry")) sender.sendMessage("\u00A7c해당 플레이어는 이미 슬픕니다.");
                else {
                    targetsModifiers.add("cry");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제 슬퍼졌습니다.");
                }
            }
            case "obfuscate" -> {
                if(targetsModifiers.contains("obfuscate")) sender.sendMessage("\u00A7c해당 플레이어의 말은 이미 알아듣기 힘듭니다.");
                else {
                    targetsModifiers.add("obfuscate");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "의 말이 이제부터 알아듣기 힘들어집니다.");
                }
            }
            case "chosung" -> {
                if(targetsModifiers.contains("chosung")) sender.sendMessage("\u00A7c해당 플레이어는 이미 초성만 쓰고 있습니다.");
                else {
                    targetsModifiers.add("chosung");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제부터 초성만 쓸 수 있게 됩니다.");
                }
            }
            case "stammer" -> {
                if(targetsModifiers.contains("stammer")) sender.sendMessage("\u00A7c해당 플레이어는 이미 말을 더듬고 있습니다.");
                else {
                    targetsModifiers.add("stammer");
                    CHAT_MODIFY.put(target, targetsModifiers);
                    sender.sendMessage(target + "은(는) 이제부터 말을 더듬습니다.");
                }
            }
            case "combine" -> {
                if(args.length == 2) sender.sendMessage("\u00A7c변경 조합을 입력해 주세요.");
                else {
                    String com = args[2];
                    if(!availableModifierCombinations.contains(com)) sender.sendMessage("\u00A7c그런 변경 조합은 없는데!");
                    else {
                        switch (com) {
                            case "shy_maid" -> {
                                if (!targetsModifiers.contains("master")) targetsModifiers.add("master");
                                if (!targetsModifiers.contains("stammer")) targetsModifiers.add("stammer");
                                if (!targetsModifiers.contains("dot")) targetsModifiers.add("dot");
                                CHAT_MODIFY.put(target, targetsModifiers);
                                sender.sendMessage(target + "에게 shy_maid 변경 조합(=master + stammer + dot)을 적용합니다.");
                            }
                            case "aroused" -> {
                                if (!targetsModifiers.contains("stammer")) targetsModifiers.add("stammer");
                                if (!targetsModifiers.contains("dot")) targetsModifiers.add("dot");
                                if (!targetsModifiers.contains("morning")) targetsModifiers.add("morning");
                                if (!targetsModifiers.contains("heart")) targetsModifiers.add("heart");
                                CHAT_MODIFY.put(target, targetsModifiers);
                                sender.sendMessage(target + "에게 aroused 변경 조합(=stammer + dot + morning + heart)을 적용합니다.");
                            }
                            case "awkward" -> {
                                if (!targetsModifiers.contains("stammer")) targetsModifiers.add("stammer");
                                if (!targetsModifiers.contains("semicolon")) targetsModifiers.add("semicolon");
                                if (!targetsModifiers.contains("dot")) targetsModifiers.add("dot");
                                CHAT_MODIFY.put(target, targetsModifiers);
                                sender.sendMessage(target + "에게 awkward 변경 조합(=stammer + semicolon + dot)을 적용합니다.");
                            }
                            case "sad" -> {
                                if (!targetsModifiers.contains("cry")) targetsModifiers.add("cry");
                                if (!targetsModifiers.contains("dot")) targetsModifiers.add("dot");
                                if (!targetsModifiers.contains("stammer")) targetsModifiers.add("stammer");
                                CHAT_MODIFY.put(target, targetsModifiers);
                                sender.sendMessage(target + "에게 sad 변경 조합(=stammer + cry + dot)을 적용합니다.");
                            }
                            case "active_maid" -> {
                                if (!targetsModifiers.contains("master")) targetsModifiers.add("master");
                                if (!targetsModifiers.contains("cute")) targetsModifiers.add("cute");
                                if (!targetsModifiers.contains("morning")) targetsModifiers.add("morning");
                                if (!targetsModifiers.contains("heart")) targetsModifiers.add("heart");
                                if (!targetsModifiers.contains("laugh")) targetsModifiers.add("laugh");
                                CHAT_MODIFY.put(target, targetsModifiers);
                                sender.sendMessage(target + "에게 active_maid 변경 조합(=master + cute + morning + heart + laugh)을 적용합니다.");
                            }
                        }
                    }
                }
            }
            case "undo" -> {
                if(args.length == 2) sender.sendMessage("\u00A7c어떤 변경자를 되돌릴지 입력해 주세요.");
                else {
                    String undo = args[2];
                    if(!availableUnmodifiers.contains(undo)) sender.sendMessage("\u00A7c그런 변경자는 없는데!");
                    else {
                        if(targetsModifiers.size() == 0 || (targetsModifiers.size() == 1 && targetsModifiers.get(0).equals(""))) sender.sendMessage("\u00A7c해당 플레이어는 이미 채팅 변경자가 적용되어 있지 않습니다.");

                        else if(undo.equals("all")) {
                            CHAT_MODIFY.put(target, new ArrayList<>());
                            sender.sendMessage(target + "의 모든 채팅 변경자를 되돌렸습니다.");
                        }
                        else {
                            if(targetsModifiers.contains(undo)) {
                                targetsModifiers.remove(undo);
                                CHAT_MODIFY.put(target, targetsModifiers);
                                sender.sendMessage(target + "의 " + undo + " 채팅 변경자를 되돌렸습니다.");



                            }
                            else sender.sendMessage("\u00A7c해당 플레이어는 이미 그 채팅 변경자가 적용되어 있지 않습니다.");
                        }
                    }
                }
            }
            default -> sender.sendMessage("\u00A7c그런 채팅 변경자는 없는데!");
        }




        return false;
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if(args.length == 1) {
            return null;
        }
        else if(args.length == 2) {
            return availableModifiers;
        }
        else if(args.length == 3) {
            if(args[1].equals("undo")) {
                return availableUnmodifiers;
            }
            else if(args[1].equals("combine")) {
                return availableModifierCombinations;
            }
        }

        return List.of();
    }
}
