Fix the Android project at /tmp/flintcrest-audio-depot so the failing quality-fix-1 step passes.

Use these orchestrator instructions: /home/codex-agent/codex-app-agent/AGENTS.md
Screen spec: /home/codex-agent/codex-app-agent/screens-shop.md
Do not push to GitHub, do not update Asana, and do not send Slack.
Fix formatting failures by expanding the affected Kotlin code; do not suppress or bypass the formatting checks.

Recent failure log:
```text
=== QUALITY CHECK: /tmp/flintcrest-audio-depot ===

WARN: Only 1 commit(s) — final implementation commit may not exist yet
  OK: Repository: 26 entries
  PLACEHOLDER-LIKE: app/src/main/res/drawable/onboarding_headphones.jpg (colors=24860, entropy=0.702698)
  PLACEHOLDER-LIKE: app/src/main/res/drawable/product_guitar.jpg (colors=22232, entropy=0.559204)
  PLACEHOLDER-LIKE: app/src/main/res/drawable/product_keyboard.jpg (colors=16962, entropy=0.870493)
  PLACEHOLDER-LIKE: app/src/main/res/drawable/product_microphone.jpg (colors=6625, entropy=0.83181)
  OK: 10 images
  OK: All images valid
FAIL: 4 placeholder-like drawable image(s); use real photos or filesystem-backed imagegen output, not local generated placeholders
  OK: No empty onClick
  OK: No obvious no-op onClick handlers
  OK: icon.png (211745B, 512x512, rounded opaque canvas, transparent corners)
FAIL: Manifest references .SkeletonApplication but class not found — CRASH
  OK: HomeScreen.kt: 216 lines
  OK: No project-local agent instruction files
  OK: dynamicColor not enabled
  OK: Google Fonts dependency found
FAIL: font_certs.xml missing
  OK: HorizontalPager used
  OK: No drawable resources detected in AsyncImage lines
  OK: Kotlin source formatting

=== RESULT: 3 error(s) ===
FIX ALL ISSUES BEFORE PUSH

```
