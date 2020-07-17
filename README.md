# Vocabulary Drills


# web-german-vocab

_To use_

* Clone the repository

* On the terminal, navigate to the repository's directory

* Run ``yarn install``

* Check the `package.json` for different scripts to run to compile the `CSS` file, build the vocabulary `edn`. If you're doing development of your own,

Demo: https://www.germanvocabdrills.com
_______________________________



_Introduction_

* This is a program I created to help learn German vocabulary.


_Description of drills_

* Word cloud: For a given vocab list, the user must write the German version perfectly for all the words in the list. The user can make errors without penalty.
* With prompts: For a given vocab list, the user is prompted with an English word, and given one chance to write the German version.

_Ideas for improvement_
* More vocabulary!
* Allow users to choose to be drilled on fewer words (i.e., a subset) or more words (i.e., an intersection of several sets)
* Allow users to continue being drilled on the vocabulary they previously missed (i.e. spaced repetition)
* Database integration
* Improve CSS

_How to add more vocabulary_

* Create a `.txt` file with your vocabulary in it.
* * The first line in the file should be the name (as displayed on the buttons, i.e. "Bike repair" for a vocabulary list about bike repair. The rest of the file has one vocabulary pair per line, with the English first and then the German, separated by a tab. It happens that, if you copy-paste two columns from an Excel spreadsheet, it will be in this tab-delimited format. Upload this file into the `/src/vocab` directory with all the rest. Create a pull request.
* Conversely, if you have a good vocabulary list in a spreadsheet format that you'd like to see uploaded, then reach out to me directly and I could do it for you.

__________________________________________________________
Alexander Crompton, Berlin, 2020 (Alexander.W.Crompton at gmail.com)
