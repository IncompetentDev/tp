package seedu.duke.parser;

import seedu.duke.commands.EditCommand;
import seedu.duke.common.Messages;
import seedu.duke.exceptions.InvMgrException;

import java.util.Optional;
import java.util.stream.Stream;

import static seedu.duke.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.duke.parser.CliSyntax.PREFIX_NAME;
import static seedu.duke.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.duke.parser.CliSyntax.PREFIX_RELATIVE;

/**
 * Parses input arguments and creates a new EditCommand object.
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws InvMgrException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws InvMgrException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_DESCRIPTION, PREFIX_RELATIVE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_QUANTITY, PREFIX_DESCRIPTION)) {
            throw new InvMgrException(Messages.INVALID_SYNTAX);
        }

        if (argMultimap.getValue(PREFIX_RELATIVE).isPresent() && !argMultimap.getValue(PREFIX_QUANTITY).isPresent()) {
            throw new InvMgrException(Messages.INVALID_RELATIVE_WITHOUT_QUANTITY);
        }

        Optional<Integer> optionalIntQuantity = Optional.empty();
        Optional<String> optionalStringQuantity = argMultimap.getValue(PREFIX_QUANTITY);
        if (optionalStringQuantity.isPresent()) {
            optionalIntQuantity = Optional.of(
                    ParserUtils.parseQuantity(optionalStringQuantity.get()));
        }

        Optional<Boolean> optionalRelativeAdd = Optional.empty();
        Optional<String> optionalStringRelativeAdd = argMultimap.getValue(PREFIX_RELATIVE);
        if (optionalStringRelativeAdd.isPresent()) {
            optionalRelativeAdd = Optional.of(
                    ParserUtils.parseRelative(optionalStringRelativeAdd.get()));
        }

        int index = ParserUtils.parseIndex(argMultimap.getPreamble()) - 1;

        return new EditCommand(index,
                argMultimap.getValue(PREFIX_NAME),
                optionalIntQuantity,
                argMultimap.getValue(PREFIX_DESCRIPTION),
                optionalRelativeAdd);
    }


    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     * For EditCommand, at least one of PREFIX_NAME, PREFIX_QUANTITY, and PREFIX_DESCRIPTION is needed.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
