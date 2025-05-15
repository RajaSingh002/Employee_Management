package view;

import java.util.List;

import utils.Constant;

public class SkillView {
    public static void showCurrentSkillsHeader() {
        System.out.println(Constant.CURRENT_SKILLS_HEADER);
    }

    public static void showNoSkillsMessage() {
        System.out.println(Constant.NO_SKILLS_MESSAGE);
    }

    public static void displaySkillTable(List<String> skills) {
        Constant.printSkillTable(skills); 
    }

    public static void showAllowedSkillsHeader() {
        System.out.println(Constant.ALLOWED_SKILLS_HEADER);
    }

    public static void promptForSkillsToAdd() {
        System.out.print(Constant.SKILL_INPUT_INSTRUCTION);
    }

    public static void showInvalidSkillInput() {
        System.out.println(Constant.INVALID_SKILL_INPUT);
    }

    public static void showSkillAdditionSuccess() {
        System.out.println(Constant.SKILL_ADDITION_SUCCESS);
    }
}
