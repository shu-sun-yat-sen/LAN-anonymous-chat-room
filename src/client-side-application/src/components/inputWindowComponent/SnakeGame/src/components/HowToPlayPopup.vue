<template>
  <v-popup @closed="$emit('closed')">
    <template #description>
      <div v-html="howToPlayMarkdownHTML"></div>
    </template>
  </v-popup>
</template>

<script lang="ts">
import VPopup from "@/components/Popup.vue";
import { marked } from "marked";
import { computed, ref } from "vue";
export default {
  components: {
    VPopup,
  },
  setup() {
    const filePath = "./HOW_TO_PLAY.md";
    const howToPlayMarkdownContent = ref<string>("Loading...");
    fetch(filePath)
      .then((response: Response) => {
        if (response.ok) return response.text();
        throw new Error(
          "**There was error fetching the [`HOW_TO_PLAY.md`].**"
        );
      })
      .catch((error: Error) => {
        return error.message;
      })
      .then((text: string) => {
        howToPlayMarkdownContent.value = text;
      });

    const howToPlayMarkdownHTML = computed(() =>
      marked(howToPlayMarkdownContent.value)
    );
    return {
      howToPlayMarkdownHTML,
    };
  },
};
</script>
