# data-lineage

Synopsis
======
Create a data lineage graph when supplied a complete list of create statements for all database objects used to create the lineage graph.

### Usage

### Steps
1. Set the database on the Stored Procedure in metadata.sql to'USE {YOUR_DB}' and run to create Common.FN_GetObjectDefinition
2. Create functions Common.FN_GetAllReferencedObjects and Common.FN_GetObjectAllDefinitions in their respective .sql files
3. Run driver program under src/main
