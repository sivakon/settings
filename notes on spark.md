# Notes on Spark

- Core Spark has SparkContext
- Spark streaming has StreamingContext
- SQLContext (Datasets, Dataframes) has SQLContext and HiveContext
    - Unified in SparkSession post 2.0

## Spark SQL has data types

- Use SparkSession to load data in DataFrames and Datasets
```
df = spark.read
          .format("json")
          .load

```

## Spark 2.0
- Dataframes are just datasets without any useful type information. Got that.
- We can only do functional programming on datasets not dataframes completely.
- **Exp** You can do functional programming on Dataframes but runtime only types :(. 
- We have to write a bunch of case statements in the case of Dataframes

