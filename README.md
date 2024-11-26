# ComPARES

ComPARES is an application to compare search results between two systems.
To compare both systems, the searches need to be exported in appropriate formats: .txt, .ris or .bib

The application then will load all the titles of each export and determine the proximity between both results.

## Installation

Once the project has been cloned locally, generate the war file and deploy it to web server.
Any Tomcat supporting Java 8 should be fine.

```bash
git clone https://github.com/isgil/ComPARES.git
```
Database expected is MySQL; To set it up, define the database details in META-INF/**persistance.xml**
Use UTF-8 encoding for the schema definition to ensure all the symbols and special characters are properly managed.

Once the application runs for the first time, the schema will be populated with all the tables required thanks to JPA configuration. As an alternative, an export of the schema with all the tables and a useful optional View created can be found in sql/**compares.sql**

View **FULL_DB_VIEW** will show a representation of the full database.
```bash
SELECT * FROM <dbSchema>.FULL_DB_VIEW;
```

## Usage

- **Library**: Lists all the tests that have been created and allow to view and export the TOPs generated by the system.
- **New Test**: Allows the creation of a new Test.
- **Settings**: Defines the number of TOPs that will be generated.

#### Library

![alt text](https://github.com/david0010/ComPARES/blob/main/doc/Library.JPG?raw=true)

- *Select ALL*: Select ALL or no tests.
- *Calculate TOPs*: Once at least 1 test has been selected, this option will produce all the TOPs possible or the number of TOPs configured in the Settings section.
- *Remove*: Removes selected test(s)
- *Show Advanced*: Shows alternative tables with more detailed results per TOP.
- *Show Basic*: Shows the original tables.
- *Export HTML*: Exports the tables (basic or advanced, depending on the option displayed) in HTML format.
- *Export CSV*: Exports the tables (basic or advanced, depending on the option displayed) in CSV format.

#### New Test

![alt text](https://github.com/david0010/ComPARES/blob/main/doc/NewTest.JPG?raw=true)

For each source, we can have 1 or more search export. System will face search exports in Pairs (Pares in Spanish); this means that we can have for each test 1 or more pairs. '+' button adds a new Pair.

Each test will require a Title, at least 1 Pair and 2 Sources. Title mark is optional.
- For .txt files, default Title Mark is "title".
- For .ris files, default Title Mark is "TI".
- For .bib files, default Title Mark is "title".

Examples of all of these types of files in **input-examples/***

#### *New source*

![alt text](https://github.com/david0010/ComPARES/blob/main/doc/NewSource.JPG?raw=true)

New sources can be added here

#### Settings

![alt text](https://github.com/david0010/ComPARES/blob/main/doc/Settings.JPG?raw=true)

Here the user can define the number of TOPs that the application will generate for each test.

## Authors and acknowledgment
Application create date: 2024

Authors:
- Isidoro Gil Leiva as creator
- David López Martínez as developer

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

## License

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
```bash
http://www.apache.org/licenses/LICENSE-2.0
```

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.