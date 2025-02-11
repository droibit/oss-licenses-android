# Development Instructions

## Updating Screenshots

Update screenshots locally by running

```bash
$ ./gradlew verifyAndRecordRoborazziDebug
```

Since screenshots may differ on Linux, Windows and Mac,
to update via github workflows run the `fixup` workflow
against the PR branch in your own repo.

https://github.com/MYUSER/oss-license-android/actions/workflows/fixup.yml
