

package view;

import java.util.List;
import utils.Constant;


/*
*********************************************************************************************************
 *  @Apex Class Name : SkillView   
 *  @Author          : Raja Kumar (raja.kumar@antrazal.com)
 *  @Company         : Antrazal
 *  @Date            : 26-05-2025
 *  @Description     : View class for displaying skill related prompts and messages
 * 
 *******************************************************************************************************
*/
public class SkillView {

    /*
     *********************************************************
     * @Method Name : showCurrentSkillsHeader
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays the header for current skills.
     * 
     * @return : void
     ********************************************************
     */
    public static void showCurrentSkillsHeader() {
        System.out.println(Constant.CURRENT_SKILLS_HEADER);
    }

    /*
     *********************************************************
     * @Method Name : showNoSkillsMessage
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays a message when no skills are found.
     * 
     * @return : void
     ********************************************************
     */
    public static void showNoSkillsMessage() {
        System.out.println(Constant.NO_SKILLS_MESSAGE);
    }

    /*
     *********************************************************
     * @Method Name : displaySkillTable
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays the list of skills using the utility method.
     * 
     * @param skills : List of skills to display.
     * 
     * @return : void
     ********************************************************
     */
    public static void displaySkillTable(List<String> skills) {
        Constant.printSkillTable(skills);
    }

    /*
     *********************************************************
     * @Method Name : showAllowedSkillsHeader
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays the header for allowed skills.
     * 
     * @return : void
     ********************************************************
     */
    public static void showAllowedSkillsHeader() {
        System.out.println(Constant.ALLOWED_SKILLS_HEADER);
    }

    /*
     *********************************************************
     * @Method Name : promptForSkillsToAdd
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Prompts user to input skills to add.
     * 
     * @return : void
     ********************************************************
     */
    public static void promptForSkillsToAdd() {
        System.out.print(Constant.SKILL_INPUT_INSTRUCTION);
    }

    /*
     *********************************************************
     * @Method Name : showInvalidSkillInput
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message for invalid skill input.
     * 
     * @return : void
     ********************************************************
     */
    public static void showInvalidSkillInput() {
        System.out.println(Constant.INVALID_SKILL_INPUT);
    }

    /*
     *********************************************************
     * @Method Name : showSkillAdditionSuccess
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays message when skill(s) added successfully.
     * 
     * @return : void
     ********************************************************
     */
    public static void showSkillAdditionSuccess() {
        System.out.println(Constant.SKILL_ADDITION_SUCCESS);
    }

    /*
     *********************************************************
     * @Method Name : showInvalidSkills
     * 
     * @author : Raja Kumar (raja.kumar@antrazal.com)
     * 
     * @Company : Antrazal
     * 
     * @description : Displays invalid skills input by the user and lists allowed
     * skills.
     * 
     * @param invalid : List of invalid skills entered.
     * 
     * @param allowed : List of allowed skills.
     * 
     * @return : void
     ********************************************************
     */
    public static void showInvalidSkills(List<String> invalid, List<String> allowed) {
        System.out.println("These skills are not allowed: " + String.join(", ", invalid));
        System.out.println("Only these skills are allowed:\n" + String.join(", ", allowed));
    }
}
