# SQL DB Connector
SQL Connection library for java applications' general purposes.

To use this library, you may download the library code or import the .jar file. The class from which you may want to connect to your SQL Database should extend DatabaseConnect.

The implementation is pretty straightforward; the constructor for DatabaseConnect requires the parameters with which the connection is made. thus, the configuration should be of no concern for the developer. Actually it supports **MYSQL, SQLSERVER, POSTGRES and DB2** databases. 

After extending the DatabaseConnect class, you may implement your own logic for the SQL operations (insert, update, delete or select), or you can just use the standard pre-built operations.
The functions supported, among others, are:

- addInsert
- addDelete
- addUpdate
- addSelect
- isConnected
- openConnection
- closeConnection
- removeAll
- removeLast
- executeAll

Contributions are well received! You may contribute to the project in several ways. You may report bugs or issues, comment how to feel or think about the project, or open a pull request, if you want a feature you have developed to be taken into account. The logical structure follows the following class diagram:
![](https://github.com/Juan7655/SqlDBConnector/blob/master/sqlDBConnect.jpeg)

If you have any questions or comments, you may email me.
Have fun!
