# Changelog

Luna shows the section for its own version in **Luna settings → Updates** after an update.
After an update, a card on the home screen shows the first 3 entries. Put the 3 most interesting changes first.
The build stops if `versionName` has no section here.

Write every entry in ASD-STE100 Simplified Technical English:

- Write one idea in each sentence. Use 20 words or fewer in each sentence.
- Use the active voice and the present tense.
- Write instructions as commands: "Select **Updates**", not "**Updates** can be selected".
- Use one word for one thing. Use the names that Luna shows on the screen.
- Start each entry with "- ". Luna shows each entry as one item. It removes `**` marks.

Put changes that are not released under `## Unreleased`. Rename that heading to the version number when you release.

## Unreleased

- **Less memory.** Luna keeps the wallpaper and the app icons once instead of twice. On a test TV, it uses about 11 MB less.
- A new **Sources** icon in the top bar opens the list of the TV inputs. Select an input to switch to it. A device that names itself over HDMI, like a PS5, shows with its logo. A port with no signal says so. Hold OK on an input to put it in the row or take it out. Inputs that were in the row before the update stay there.
- **Remote keys.** Change what a button on your remote does. Select **Luna settings → Remote keys → Add a key**. Press the key on your remote. Then choose what a press does and what a hold does. A key can play or pause, go back, or open an app or an input. It can also open Android settings or the network settings, or do nothing. If you set a PIN in **Lock layout**, Luna asks for it first.
- A long app name no longer runs past its tile. It scrolls slowly while the app is selected.
- **Uninstall an app.** Hold **OK** on an app in the row. Select **Uninstall**. Luna does not show this for system apps, for Luna, or when the layout is locked.
- **Luna settings → App row → Icon style** has a new choice, **Banner**. Each app shows its own picture, as on Apple TV. An app with no banner gets its own colour behind a white mark. Monochrome stays the default.
- **Luna settings → App row → Icon style** has a fourth choice, **Monochrome banner**. The pictures are grey until you select one. An app with no banner gets its own colour behind a white mark, as with **Banner**.

## 1.6

- Rename Plume to Luna in all text and resources.
- Set the update and gallery URLs to the new Luna repository.

## 1.5

### New
- **Pick your bottom row.** Show any channel from your apps, such as a Stremio library. Select **Luna settings → Bottom row → Channels**. The row takes the channel's name, or a name you set in **Row name**.
- **Bing wallpaper.** Use the Bing picture of the day as your wallpaper. Select **Luna settings → Appearance → Light mode wallpaper** or **Dark mode wallpaper**, then select **Bing picture of the day**.
- **New languages.** Luna is now available in English, French, Turkish and Arabic. It uses the language of your TV.
- **PIN for Wi-Fi and Android settings.** If you set a PIN, Luna asks for it before it opens Wi-Fi or Android settings from the top bar.

### Design
- **See-through tiles.** The wallpaper shows through the tiles. Menus and dialogs stay solid.
- **Smooth gradients.** The dark tiles and the wallpaper fade no longer show bands.
- **Smoother scrolling.** The app row moves more smoothly.

### Easier to use
- **Continue Watching order.** What you are watching comes before the next-episode suggestions. Luna also reads the time correctly from apps that give it in seconds.
- **App names.** When you move quickly along the app row, Luna shows an app's name only when you reach that app.

### Fixes
- Luna opens on Android 8.0 and 8.1. Before, it closed immediately after it opened.
- If your wallpaper is too big for the memory of your TV, Luna shows the default wallpaper. Before, Luna closed.
- In **All apps**, the tile in the first or last column is no longer cut off when you select it.

## 1.4

### Smaller
- **12% smaller.** Luna is now 365 KB, down from 413 KB.

### New
- **Consoles on your home screen.** Your PS5, Xbox or Nintendo Switch gets a tile with its logo. Select it to switch to that input.
- **More Continue Watching.** The limit of 3 shows for each app is gone. Each app keeps 3 places at the front.
- **Every HDMI input as a tile.** Other HDMI devices get a tile while they are on. All of them are in **All apps**.
- **What's new card.** After an update, a card shows the highlights. Select **See all changes** to read everything.

### Design
- **New dark mode.** True black, with lit edges on the selected item.
- **Luna settings, organised.** Settings are in sections. Each section shows its current settings.
- **App row position.** Put a short app row on the left, in the centre or on the right. Select **Luna settings → App row → App row position**.

### Easier to use
- **Faster settings.** Push Left or Right to change a value. Settings opens where you left it. At the top of a list, push Up to go to the bottom.
- **Weather location.** Set it by hand in **Luna settings → Weather → Weather location**.
- **Home button setup.** A guided setup makes the Home button open Luna. Select **Luna settings → Home button → Set up Home button**. **Home button** is now an on/off switch.
- **Update dot.** A red dot on the Luna settings icon tells you about an update. **Luna settings → Updates** shows what is new.

### Fixes
- After the TV starts, the first app is selected, not the network icon.
- The hold OK menu is only as wide as its items.

## 1.3

- You can use a folder of photos as your wallpaper. The folder can be on the TV or on a USB drive. Select **Luna settings → Folder photo changes** to set how often the photo changes.
- **Photo fade** removes the dark area at the bottom of your photos.
- You can use your photos as the screensaver. Select **Luna settings → Screensaver → Your photos**.
- **Lock layout** keeps the home screen safe from changes. Set a 4-digit PIN in **Luna settings → Lock layout**. Apps still open without the PIN.
- Hold OK on an app to show **Move**, **Remove from row** and **App info**.
- Hold OK on a Continue Watching card to show **Remove** and **Open app**.
- Continue Watching shows a maximum of three shows from each app.
- **Launcher settings** is now **Luna settings**. Back goes up one page.
- **Remove a photo…** deletes wallpapers that you uploaded.
- The Home button switch opens the correct settings screen on more TVs.

## 1.2.1

- Fix: with **Icon style → Colour**, some app icons stayed grey until Luna started again.

## 1.2

- Luna updates itself. It looks for a new version one time each day. Select **Luna settings → Updates** to install it.
- You can hide apps from the home row. Select **Luna settings → Apps in row**. Hidden apps stay in All apps.
- **App names** shows app names below the tiles: on focus, always or never.
- **Icon style → Colour** shows each logo in the colour of its brand.
- You can add wallpapers from your phone. Select **Add from phone…** in a wallpaper list, then scan the QR code.
- Fix: green areas no longer show in the sky of wallpapers.

## 1.1

- The APK is now 0.4 MB. It was 5.1 MB.
- The Gallery screensaver paintings are now a 4.7 MB download. Select **Luna settings → Screensaver → Gallery** to get them.
- Luna deletes the paintings when you select a different screensaver.
- Fix: Luna did not always find its Home button service.

## 1.0

- First release.
