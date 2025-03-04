package seedu.duke.stubs;

import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DescCommand;
import seedu.duke.commands.EditCommand;
import seedu.duke.commands.SearchCommand;
import seedu.duke.data.Item;

import java.util.Optional;

/* Contains various stubs and constants used for testing. */
public class ParserStubs {
    public static final String PAPERCUP_NAME = "Paper Cup";
    public static final String PAPERCUP_QUANTITY = "25";
    public static final String PAPERCUP_SETQUANTITY = "23";
    public static final String PAPERCUP_SETRELATIVEQUANTITY = "2";
    public static final String PAPERCUP_DESCRIPTION = "25ml cups";

    public static final Item PAPERCUP_ITEM = new Item(ParserStubs.PAPERCUP_NAME,
            Integer.parseInt(PAPERCUP_QUANTITY), ParserStubs.PAPERCUP_DESCRIPTION);
    public static final AddCommand PAPERCUP_ADDCOMMAND = new AddCommand(PAPERCUP_ITEM);
    public static final DeleteCommand ZEROINDEX_DELETECOMMAND = new DeleteCommand(0);
    public static final DescCommand ZEROINDEX_DESCCOMMAND = new DescCommand(0);
    public static final EditCommand ZEROINDEX_EDITCOMMAND_QUANTITYONLY = new EditCommand(0,
            Optional.empty(),
            Optional.of(23),
            Optional.empty(),
            Optional.empty());
    public static final EditCommand ZEROINDEX_EDITCOMMAND_NEGATIVERELATIVEQUANTITY = new EditCommand(0,
            Optional.empty(),
            Optional.of(2),
            Optional.empty(),
            Optional.of(false));
    public static final SearchCommand SEARCHCOMMAND_NAMEONLY = new SearchCommand(
            Optional.of(PAPERCUP_NAME), Optional.empty());
    public static final SearchCommand SEARCHCOMMAND_DESCRIPTIONONLY = new SearchCommand(
            Optional.empty(), Optional.of(PAPERCUP_DESCRIPTION));
    public static final SearchCommand SEARCHCOMMAND_NAMEANDDESCRIPTION = new SearchCommand(
            Optional.of(PAPERCUP_NAME), Optional.of(PAPERCUP_DESCRIPTION));

}

