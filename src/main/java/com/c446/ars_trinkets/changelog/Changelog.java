package com.c446.ars_trinkets.changelog;

import java.util.List;

/**
 * Single source of truth for the in-game changelog (login message + Ars Nouveau book).
 * Text lives in en_us.json under {@code ars_trinkets.changelog.<version with _>.line<N>}.
 * To add a release: add an entry at the top of {@link #RELEASES} and its lang lines.
 */
public final class Changelog {
    public record Release(String version, int lines) {
        public String lineKey(int index) {
            return "ars_trinkets.changelog." + version.replace('.', '_') + ".line" + index;
        }

        /** Title of this release's book entry; also the entry id path. */
        public String entryName() {
            return "changelog_" + version.replace('.', '_');
        }
    }

    /** Newest first. */
    public static final List<Release> RELEASES = List.of(
            new Release("3.1.0", 7),
            new Release("3.0.0", 4)
    );

    private Changelog() {
    }

    public static Release forVersion(String version) {
        for (Release release : RELEASES) {
            if (release.version().equals(version)) return release;
        }
        return null;
    }
}
