package seedu.duke.parser;

import seedu.duke.commands.Command;
import seedu.duke.commands.DescCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.ListCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.SearchCommand;
import seedu.duke.exceptions.InvMgrException;
import seedu.duke.common.Messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Manages the user input.
     * 
     * @param userCommand User input
     * @return A command that the user requested for to be executed
     * @throws InvMgrException Exception used for handling invalid inputs.
     */
    public static Command parse(String userCommand) throws InvMgrException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userCommand.trim());

        if (!matcher.matches()) {
            throw new InvMgrException(Messages.INVALID_COMMAND);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DescCommand.COMMAND_WORD:
            return new DescCommandParser().parse(arguments);

        case SearchCommand.COMMAND_WORD:
            return new SearchCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        default:
            throw new InvMgrException(Messages.INVALID_COMMAND);
        }
    }
}
