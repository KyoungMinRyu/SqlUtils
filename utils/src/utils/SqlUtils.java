package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SqlUtils {
	public static final String DATE = "yyyy-MM-dd";
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm";
    public static final String TIME = "HH:mm";

    public LocalDateTime now() {
        return LocalDateTime.now();
    }

    public LocalDate dateAdd(LocalDate date, int days) {
        return date.plusDays(days);
    }

    public LocalDateTime dateAdd(LocalDateTime date, int days) {
        return date.plusDays(days);
    }

    public long dateDiff(LocalDate date1, LocalDate date2) {
        return java.time.Duration.between(date1.atStartOfDay(), date2.atStartOfDay()).toDays();
    }

    public long dateDiff(LocalDateTime date1, LocalDateTime date2) {
        return java.time.Duration.between(date1, date2).toDays();
    }

    public String concat(String... strings) {
        return concat("", strings);
    }

    public String concat(String delimiter, String... strings) {
        return isNull(strings)
            ? ""
            : Arrays.stream(strings)
            .filter(Objects::nonNull)
            .collect(Collectors.joining(delimiter));
    }

    public String groupConcat(List<String> list) {
        return groupConcat(list, ",");
    }

    public String groupConcat(List<String> list, String delimiter) {
        return isNull(list)
            ? ""
            : list.stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toCollection(LinkedHashSet::new))
            .stream()
            .sorted()
            .collect(Collectors.joining(delimiter));
    }

    public <T> T coalesce(T... values) {
        return Arrays.stream(values)
            .filter(Objects::nonNull)
            .findFirst()
            .orElse(null);
    }

    public String ifNull(String str) {
        return ifNull(str, "");
    }

    public String ifNull(String str, String def) {
        return isNull(str) ? def : str;
    }

    public int ifNull(Integer number) {
        return ifNull(number, 0);
    }

    public long ifNull(Long number) {
        return ifNull(number, 0L);
    }

    public double ifNull(Double number) {
        return ifNull(number, 0.0);
    }

    public <T> T ifNull(T value, T def) {
        return isNull(value) ? def : value;
    }

    public String nvl(String str) {
        return nvl(str, "");
    }

    public String nvl(String str, String def) {
        return isNull(str) ? def : str;
    }

    public int nvl(Integer number) {
        return nvl(number, 0);
    }

    public long nvl(Long number) {
        return nvl(number, 0L);
    }

    public double nvl(Double number) {
        return nvl(number, 0.0);
    }

    public <T> T nvl(T value, T def) {
        return isNull(value) ? def : value;
    }

    public boolean isNull(String str) {
        return str == null || str.trim().isEmpty();
    }

    public <T> boolean isNull(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public <T> boolean isNull(T[] array) {
        return array == null || array.length == 0;
    }

    public boolean isNull(Object obj) {
        return obj == null;
    }

    public String dateFormat(LocalDate date) {
        return dateFormat(date, DATE);
    }

    public String dateFormat(LocalDate date, String pattern) {
        if (isNull(date)) {
            return "";
        }
        try {
            return date.format(DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException e) {
            return "";
        }
    }

    public String dateFormat(LocalDateTime dateTime) {
        return dateFormat(dateTime, DATE_TIME);
    }

    public String dateFormat(LocalDateTime dateTime, String pattern) {
        if (isNull(dateTime)) {
            return "";
        }
        try {
            return dateTime.format(DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException e) {
            return "";
        }
    }

    public int sum(List<Integer> integers) {
        return isNull(integers)
            ? 0
            : sum(integers.toArray(new Integer[0]));
    }

    public int sum(Integer... integers) {
        return isNull(integers)
            ? 0
            : Arrays.stream(integers)
            .filter(Objects::nonNull)
            .mapToInt(Integer::intValue)
            .sum();
    }

    public int max(List<Integer> integers) {
        return isNull(integers)
            ? 0
            : max(integers.toArray(new Integer[0]));
    }

    public int max(Integer... integers) {
        return isNull(integers)
            ? 0
            : Arrays.stream(integers)
            .filter(Objects::nonNull)
            .mapToInt(Integer::intValue)
            .max()
            .orElse(Integer.MAX_VALUE);
    }

    public int min(List<Integer> integers) {
        return isNull(integers)
            ? Integer.MAX_VALUE
            : min(integers.toArray(new Integer[0]));
    }

    public int min(Integer... integers) {
        return isNull(integers)
            ? Integer.MAX_VALUE
            : Arrays.stream(integers)
            .filter(Objects::nonNull)
            .mapToInt(Integer::intValue)
            .min()
            .orElse(0);
    }

    public <T> List<T> union(List<T> list1, List<T> list2) {
        if (isNull(list1) && isNull(list2)) {
            return List.of();
        }
        if (isNull(list1)) {
            return list2;
        }
        if (isNull(list2)) {
            return list1;
        }
        return Stream.concat(list1.stream(), list2.stream()).distinct().toList();
    }

    public <T> List<T> unionAll(List<T> list1, List<T> list2) {
        if (isNull(list1) && isNull(list2)) {
            return List.of();
        }
        if (isNull(list1)) {
            return list2;
        }
        if (isNull(list2)) {
            return list1;
        }
        return Stream.concat(list1.stream(), list2.stream()).toList();
    }
}
