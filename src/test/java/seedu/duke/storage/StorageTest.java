package seedu.duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Item;
import seedu.duke.exceptions.InvMgrException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class StorageTest {
    private static final String TEST_DATA_FILE_DELETE_ERR = "Could not clean up the generated test data file!";

    @Test
    void load_emptyFile_loadEmptyList() throws InvMgrException {
        Storage testStorage = new Storage("test/data/load/empty.json");
        ArrayList<Item> testItemList = testStorage.load();
        ArrayList<Item> expectedItemList = new ArrayList<>();
        assertEquals(expectedItemList, testItemList);
    }

    @Test
    void load_invalidJsonFile_throwException() throws InvMgrException {
        Storage testStorage = new Storage("test/data/load/invalidData.json");
        assertThrows(InvMgrException.class, () -> testStorage.load());
    }

    @Test
    void load_validJsonFile_validList() throws InvMgrException {
        ArrayList<Item> expectedItemList = new ArrayList<>();
        Item item1 = new Item("Markers", 3, "Drawing");
        Item item2 = new Item("Whiteboard", 1, "To draw on");
        Item item3 = new Item("HDMI Cable", 2, "For connecting displays");
        expectedItemList.add(item1);
        expectedItemList.add(item2);
        expectedItemList.add(item3);

        Storage testStorage = new Storage("test/data/load/validInputData.json");
        ArrayList<Item> testItemList = testStorage.load();

        assertListEquals(expectedItemList, testItemList);
    }

    @Test
    void save_nullList_throwException() throws InvMgrException {
        Storage testStorage = new Storage("test/data/save/actualData.json");
        assertThrows(NullPointerException.class, () -> testStorage.save(null));
    }

    @Test
    void save_validList_validJson() throws InvMgrException {
        ArrayList<Item> itemList = new ArrayList<>();
        Item item1 = new Item("Markers", 3, "Drawing");
        Item item2 = new Item("Whiteboard", 1, "To draw on");
        Item item3 = new Item("HDMI Cable", 2, "For connecting displays");
        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);

        Storage testStorage = new Storage("test/data/save/actualData.json");
        testStorage.save(itemList);

        Storage expectedStorage = new Storage("test/data/save/expectedData.json");
        assertStorageEquals(expectedStorage, testStorage);
    }

    private void assertStorageEquals(Storage expectedStorage, Storage testStorage) {
        Path testPath = testStorage.getDataPath();
        Path expectedPath = expectedStorage.getDataPath();

        try {
            List<String> testString = Files.readAllLines(testPath);
            List<String> expectedString = Files.readAllLines(expectedPath);
            assertEquals(expectedString, testString);
        } catch (IOException e) {
            fail(e);
        }

        try {
            Files.deleteIfExists(testPath);
        } catch (IOException e) {
            System.err.println(TEST_DATA_FILE_DELETE_ERR);
        }
    }

    /**
     * Constructs a HashSets to do unordered comparison of two {@code ArrayList<Item>}.
     *
     * @param expectedList the list to expect
     * @param actualList the list to check against
     */
    private void assertListEquals(ArrayList<Item> expectedList, ArrayList<Item> actualList) {
        HashSet<String> checker = new HashSet<String>();
        for (int i = 0; i < actualList.size(); i++) {
            checker.add(expectedList.get(i).saveString());
            checker.add(actualList.get(i).saveString());
        }
        assertEquals(checker.size(), actualList.size());
    }
}
