# xml-parsing-benchmark
Some code for testing XML parsing speeds across various libraries in Java and Groovy

I wrote a few scripts to compare parsing speeds between different libraries using a large XML dataset (NVD's 2011 CVE data set, 116MB of XML).

The XML is in the format kind of like this:

```
<entry>
  <element1>value</element1>
  <element2>value</element2>
  <element3>value</element3>
  <element4>value</element4>
  <element5>value</element5>
  <element6>value</element6>
  <element7>value</element7>
  <element8>value</element8>
</entry>
<entry>
  <element1>value</element1>
  <element2>value</element2>
  <element3>value</element3>
  <element4>value</element4>
  <element5>value</element5>
  <element6>value</element6>
  <element7>value</element7>
  <element8>value</element8>
</entry>
```

We are only grabbing the even elements and storing them in an array of hashmaps. (ie ```[{element2: value, element4: value..},{..}]```)

We then print the time it took to do this, and close.

In the NVD 2011 dataset there are more than 4000 entries, we test each method manually by printing out a known value (ie entry 4000) in dev to make sure the data is being parsed correctly.

Using the bash script below, we can run each method as many times as we want and log the time entries to a file where we can work out averages. We can also pass in different parameters for the JVM (ie min and max heaps size, -Xms512m and -Xmx16g)

##Java
```for((i=1; i<10; i++)); do java -cp compiled.jar MethodClassName; done | tee Method.log```

##Groovy
```for((i=1; i<100; i++)); do groovy GroovyScriptName.groovy; done | tee Method.log```
