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