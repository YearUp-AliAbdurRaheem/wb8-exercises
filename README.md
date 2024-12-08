<H1 align="center">
wb8-exercises
</H1>

<p align="center">
  <img src="src/main/resources/Images/Log4J.png" alt="Log4J Logo" width="80"/>
</p>

## Log4J
In [`Main.java`](src/main/java/com/pluralsight/Main.java) you will find an example/test for `Logging` with `Log4J`.
The [`pom.xml`](pom.xml) file has dependencies for `Log4J`. 
It has a dependency manager with a **BOM** (Bill Of Materials), 
that makes sure the versions are consistent and that you only have to type the version number once; for example: `2.24.2`.
`Log4J` also uses a config file, in this case [`log4j2.properties`](src/main/resources/log4j2.properties) is our file.

<details>
    <summary><code>log4j2.properties</code></summary>

```xml
<code>
    <Configuration status="WARN">
        <Appenders>
            <Console name="Console" target="SYSTEM_OUT">
                <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1} - %m%n"/>
            </Console>
        </Appenders>
        <Loggers>
            <Root level="debug">
                <AppenderRef ref="Console"/>
            </Root>
        </Loggers>
    </Configuration>
</code>
```
</details>

These are Apache docs on [`Getting Started with Log4J`](https://logging.apache.org/log4j/2.x/manual/getting-started.html).


This project was built with <img src="src/main/resources/Images/Log4J-compact.webp" alt="Log4J Logo" width="30"/>
