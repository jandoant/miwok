#Miwok-App

###v.01 - Initial Commit
* Categories: Numbers, Family Members, Colors, Phrases
* **Main Activity**
    * List of `TextViews` for each Categroy
    * each category has a color
    * click on one categoryTextView --> Open Category Activity
        + explicit Intent
* **Numbers Activity**
    * `ArrayList<String>` of ten English Words for Numbers
    * Populated `ListView` with default `ArrayAdapter<String>` and default List Item View

###v.02 - Create Custom List View
* Layout of Single List Item defined in`list_row.xml`
    * contains of 2 `TextViews`
* Word class created
* **Main Activity**
    * no changes
* **Numbers Activity**
    * show `ListView` of `ArrayList<Word>` -- Custom `WordAdapter`
    `
###v.03 - Vocabulary List completed
* Miwok words for all categories added in `ArrayList<Word> words` in each Category Activity

###v.04 - Display Image for each word
* add ImageView to `list_row.xml`
* add Images to `res/drawable`-Folder
* add `int image` field to `Word` class --> Constructor, getter, setter
* update `ArrayList<Word> words` in each category to initialize Image-Resource for each word
* update `WordAdapter` to populate Image resource in `getView()`
* no images shown on Phrases

###v.05 - Visual Polish of app
* style list items
    * image height, width, margin,layout gravity
    * background has category color
