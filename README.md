![Static Badge](https://img.shields.io/badge/License-MIT-yellow?link=https%3A%2F%2Fopensource.org%2Flicense%2FMIT)
[![Package Deploy](https://github.com/KrzyZyb/XLS2SQL/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/KrzyZyb/XLS2SQL/actions/workflows/maven-publish.yml)
[![Build](https://github.com/KrzyZyb/XLS2SQL/actions/workflows/maven.yml/badge.svg)](https://github.com/KrzyZyb/XLS2SQL/actions/workflows/maven.yml)


<!DOCTYPE html>
<html lang="en">
<body>
<div class="markdown prose w-full break-words dark:prose-invert light">
  <h1>XLS2SQL</h1>
  <p>The XLS to SQL Processor library provides functionality to map Excel files to SQL scripts that insert rows into a
    data table. It offers two endpoints for processing Excel files and generating SQL scripts.</p>
  <h2>Features</h2>
  <ul>
    <li>Process Excel files (XLS format) and generate SQL scripts for data insertion.</li>
    <li>Customize output SQL scripts with specified table names and column names.</li>
    <li>Validate Excel files and configurations to ensure data integrity.</li>
  </ul>
  <h2>Usage</h2>
  <h3>Default Processing Method</h3>
  <pre><div class="dark bg-gray-950 rounded-md border-[0.5px] border-token-border-medium"><div
      class="flex items-center relative text-token-text-secondary bg-token-main-surface-secondary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><div
      class="flex items-center"><span class="" data-state="closed"></span></div></div><div class="p-4 overflow-y-auto"><code
      class="!whitespace-pre hljs language-java">Xls2SqlProcessor.process(Path inputFile, Path outputFile)
</code></div></div></pre>
  <p>In the default processing method, <code>Xls2SqlProcessor.process</code>, provide the path to the input Excel file (<code>inputFile</code>)
    containing the data to insert and the path to the output SQL script file (<code>outputFile</code>). The library
    automatically generates an SQL script with column names extracted from the XLS header and the table name derived
    from the output file name.</p>
  <h3>Custom Processing Method</h3>
  <pre><div class="dark bg-gray-950 rounded-md border-[0.5px] border-token-border-medium"><div
      class="flex items-center relative text-token-text-secondary bg-token-main-surface-secondary px-4 py-2 text-xs font-sans justify-between rounded-t-md"><div
      class="flex items-center"><span class="" data-state="closed"></span></div></div><div class="p-4 overflow-y-auto"><code
      class="!whitespace-pre hljs language-java">Xls2SqlProcessor.process(Path inputFile, Path outputFile, OutputFileConfig config)
</code></div></div></pre>
  <p>In the custom processing method, <code>Xls2SqlProcessor.process</code>, provide the path to the input Excel file
    (<code>inputFile</code>), the path to the output SQL script file (<code>outputFile</code>), and a custom
    configuration (<code>config</code>). The configuration (<code>OutputFileConfig</code>) allows you to customize the
    table name and column names for the output SQL script.</p>
  <h2>Example</h2>
  <div><code>Path inputFile = Paths.get("./user/Source.xls");</code></div>
  <div><code>Path outputFile = Paths.get("./user/Destiny.sql");</code></div>
<br>
<div>// Default processing method</div>
<div><code>Xls2SqlProcessor.process(inputFile, outputFile);</code></div>
<br>
<div>// Custom processing method with configuration</div>
<div><code>OutputFileConfig config = new OutputFileConfig("CustomTableName", Arrays.asList("Column1", "Column2"));</code></div>
<div><code>Xls2SqlProcessor.process(inputFile, outputFile, config);</code></div>
</code></div></div></pre>
  <h2>Requirements</h2>
  <ul>
    <li>Java 8 or higher</li>
  </ul>
  <h2>License</h2>
  <p>This library is licensed under the MIT License.</p>
</div>
</body>
</html>
