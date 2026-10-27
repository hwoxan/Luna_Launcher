# Luna Launcher

Um launcher de TV minimalista e leve.

### Como instalar
1. Baixe o APK mais recente das [Releases](https://github.com/hwoxan/Luna_Launcher/releases).
2. Instale via adb:
   ```bash
   adb install -r app-release.apk
   ```
3. Conceda permissões necessárias (deve ser feito uma vez):
   ```bash
   adb shell pm grant dev.luna android.permission.WRITE_SECURE_SETTINGS
   ```

### Personalização
Você pode copiar arquivos `.jpg`, `.png` ou `.webp` para `Android/data/dev.luna/files/wallpapers/` para usar como papel de parede personalizados:
```bash
adb push meu-papel-de-parede.jpg /sdcard/Android/data/dev.luna/files/wallpapers/
```
