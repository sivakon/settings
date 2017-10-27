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
- We can only do **functional programming on datasets** not dataframes completely.
- **Exp** You can do functional programming on Dataframes but runtime only types :(. 
- We have to write a bunch of case statements in the case of Dataframes like below

```
import org.apache.spark.sql.Row
val words = textFile.
             select("value").
             flatMap{case Row(value: String) => value.split(" ")}
val counts = words.
             groupBy($"value").
             count() 
```

## Machine learning here

```
//get input features
val assembler = new VectorAssembler()
assembler.setInputCols(Array())
//string indexer to get string features

val pipe = new Pipeline()
pipe.setStages(assembler, stringer)

val dt = new DecisionTreeClassifier()
dt.setFeaturesCol()
dt.setPredictionCol()

//add to pipeline
pipe.setStages(Array(,,))
pipemlModel = pipeline.fit(df)

```

### Window expressions
`sql.expressions.Windows`
val a = Window.partitionBy().orderBy().rowsBetween()
df.select(avg("").over(a))

# Other discoveries

- Check out databricks
- https://docs.databricks.com/user-guide/faq/running-c-plus-plus-code.html
- talk is [here](https://www.youtube.com/watch?v=jbclxiAaKFQ)
