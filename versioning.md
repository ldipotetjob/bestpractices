This is not specifically a best practices file It is just about versioning it's just more a pattern 
about how to versioning softwares/docker images/releases:

#### Some patterns

* SNAPSHOT version: version before any release version. For example before 1.0-release:</br>
1.0-SNAPSHOT


| Stage             | Semver        | Num. Status  |
| ----------------- |:-------------:| ------------:|
| Alpha             | 1.2.0-a.1     | 1.2.0.1      |
| Beta              | 1.2.0-b.2     | 1.2.1.2      |
| Release candidate | 1.2.0-rc.3    | 1.2.2.3      |
| Release           | 1.2.0         | 1.2.3.0      |


**Versioning patterns**: https://semver.org/
